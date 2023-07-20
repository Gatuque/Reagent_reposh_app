(ns app.articles
  (:require [ajax.core :refer [GET json-response-format]]
            [reagent.core :as r]))

(defonce api-url "https://conduit.productionready.io/api")
(defonce articles-state (r/atom nil))


(defn handler
  [response]
  (reset! articles-state response))

(defn error-handler
  [{:keys [status status-text]}]
  (.log js/console (str "something went wrong : " status " " status-text)))

(defn browse-artices
  []
  (GET (str api-url "/articles/?limit=25") {:handler handler
                                            :response-format (json-response-format {:keywords? true})
                                            :error-handler error-handler}))

