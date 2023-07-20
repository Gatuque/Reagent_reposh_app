(ns app.auth
  (:require [ajax.core :refer [GET POST json-response-format
                               json-request-format]]
            [reagent.core :as r]))

(def auth-state (r/atom nil))

(defonce api-url "https://conduit.productionready.io/api")

(defn error-handler
  [{:keys [status status-text] :as error}]
  (js/console.log (str "something went wrong : " status " " error)))

(defn auth-success! 
  [{{:keys [token] :as user} :user}]
  (prn "Token " token " User " user)
  (.setItem js/localStorage "auth-user-token" (:token user))
  (reset! auth-state user))


(defn login! [login-input]
  (POST (str api-url "/users/login") 
    {:params {:user login-input} 
     :handler auth-success! 
     :format (json-request-format)
     :response-format (json-response-format {:keywords? true}) 
     :error-handler error-handler}))

(defn register! [registration-input] 
  (POST (str api-url "/users") 
    {:params {:user registration-input} 
     :handler auth-success! 
     :format (json-request-format) 
     :response-format (json-response-format {:keywords? true}) 
     :error-handler error-handler}))

(comment 
  (register! {:username "aktmrkggra"
              :email "geghkahd@gmail.com"
              :password "paughkmfmodgrd"}) 
  
  (login! {:username "aktmrkggra"
              :email "geghkahd@gmail.com"
              :password "paughkmfmodgrd"}) 
  
  @auth-state 
  (.setItem js/localStorage "my-name" "George Gatuma")
  (.getItem js/localStorage "auth-user-token")
  (.removeItem js/localStorage "auth-user-token")


  )

