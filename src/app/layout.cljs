(ns app.layout
  (:require [app.components.header :refer [header]]
            [app.routes :refer [route-state]]))


(defn app []
  [:div
   [header]
   (let [current-view (-> (deref route-state) :data :view)]
     [current-view])])
