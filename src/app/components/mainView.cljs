(ns app.components.mainView
  (:require [app.articles :refer [articles-state]]
            [app.components.articles :refer [articles]]))

(defn mainView []
  [:div.col-md-9>div.feed-toogle
   [:ul.nav.nav-pills.outline-active
    [:li.nav-item
     [:a.nav-link.active
      {:href ""} "Global Feed"]]]
   [articles (deref articles-state)]])