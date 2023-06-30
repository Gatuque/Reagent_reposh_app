(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.core :as r])) 

(defonce mock-articles
  [{:title "Finaly cracking frontend mystic"}
   {:title "Not an easy as I had thought"}])

(defn header []
  [:nav {:className "navbar navbar-light"}
   [:div {:className "container"}
    [:a {:className "navbar-brand"} "conduit"]]])

(defn banner [token]
 (when token 
   [:div.banner>div.container 
    [:h1.logo-front "conduit"] 
    [:p "A place to share your knowledge"]]))

(defn articles [items]
  (if-not (seq items)
    [:div.artile-preview "loading"]
    (if (= 0 (count items))
      [:div.artile-preview "No artiles found"]
      [:div
       (for [article items]
         [:h2 (:title article)])])))

(defn mainView []
  [:div.col-md-9>div.feed-toogle
   [:ul.nav.nav-pills.outline-active
    [:li.nav-item
     [:a.nav-link.active
      {:href ""} "Global Feed"]]]
   [articles mock-articles]])


(defn home-page []
  [:div.home-page
   [banner "auth-user-token"]
   [:div.container.page>div.row
    [mainView]
    [:div.col-md-3
     [:div.sidebar 
      [:p "Popular tags"]]]]])


(defn app []
  [:div 
   [header]
   [home-page]])

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export main
  "Run application startup logic."
  []
  (render))
