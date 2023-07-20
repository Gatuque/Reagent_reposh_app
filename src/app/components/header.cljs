(ns app.components.header
  (:require [reitit.frontend.easy :as rfe]
            [app.routes :as routes]))

(defn header []
  [:nav {:className "navbar navbar-light"}
   [:div {:className "container"}
    [:a {:className "navbar-brand" :href (rfe/href ::routes/home)} "conduit"]
    [:ul.nav.navbar-nav.pull-xs-right
     [:li.nav-item
      [:a.nav-link {:href (rfe/href :home)} "Home"]]
     [:li.nav-item
      [:a.nav-link {:href (rfe/href :login)} "Login"]]
     [:li.nav-item
      [:a.nav-link {:href (rfe/href :register)} "Sign Up"]]
     [:li.nav-item
      [:a.nav-link {:href (rfe/href :settings)} "Settings"]]]]])


;; create a siple card with a single button