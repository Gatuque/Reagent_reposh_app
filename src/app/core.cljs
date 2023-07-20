(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.core :as r]
            [app.layout :refer [app]]
            [app.routes :refer [router-start!]]
            [app.articles :refer [browse-artices]]))


(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export main
  "Run application startup logic."
  [] 
  (router-start!) 
  (browse-artices) 
  (render))


;; (comment
;;   (browse-artices) 
;;   (deref articles-state) 

;;   (-> @route-state :data :view)

;;   ;;takes a route name and generates a the route path, or nil
;;   (rfe/href ::home)
;;   )