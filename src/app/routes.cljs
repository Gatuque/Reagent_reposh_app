(ns app.routes
  (:require [reitit.frontend :as rf]
            [reagent.core :as r]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.spec :as rss] 
            [app.pages.home :refer [home-page]]
            [app.pages.settings :refer [settings-page]]
            [app.pages.login :refer [login-page]]
            [app.pages.register :refer [register-page]]))

(defonce route-state (r/atom nil))

(def routes
  [["/"  {:name :home :view #'home-page}]

   ["/login" {:name :login :view #'login-page}]

   ["/register" {:name :register :view #'register-page}]

   ["/settings" {:name :settings :view #'settings-page}]])

(defn router-start!
  []
  (rfe/start!
   (rf/router routes {:data {:coercion rss/coercion}})
   (fn [matched-route] 
     (reset! route-state matched-route))
      ;; set to false to enable HistoryAPI
   {:use-fragment false}))