(ns app.pages.home
  (:require [app.components.banner :refer [banner]]
            [app.components.mainView :refer [mainView]]))


(defn home-page []
  [:div.home-page
   [banner "auth-user-token"]
   [:div.container.page>div.row
    [mainView]
    [:div.col-md-3
     [:div.sidebar
      [:p "Popular topics"]]]]])