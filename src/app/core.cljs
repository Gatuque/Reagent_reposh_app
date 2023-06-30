(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.core :as r]
            [ajax.core :refer [GET json-response-format]]))

(defonce api-url "https://conduit.productionready.io/api")

;;states
(defonce articles-state (r/atom ""))

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

(defn header []
  [:nav {:className "navbar navbar-light"}
   [:div {:className "container"}
    [:a {:className "navbar-brand"} "conduit"]]])

(defn banner [token]
 (when token 
   [:div.banner>div.container 
    [:h1.logo-front "conduit"] 
    [:p "A place to share your knowledge"]]))


(defn article-preview [{:keys [title description author createdAt
                               favoritesCount tagList]}]
  [:div.artile-preview
   [:div.article-meta
   [:a 
    [:img {:src (author :image)}]]
    [:div.info
     [:a.author (author :username)]
     [:span.date (.toDateString (new js/Date createdAt))]]
    [:div.pull-xs-right 
     [:button.btn.btn-sm.btn-outline-primary
      [:i.ion-heart favoritesCount]]]]
   [:a.preview-link 
   [:h1 title]
   [:p description]
   [:span "Read more ..."]
   [:ul.tag-list
   (for [tag tagList] 
     [:li.tag-default.tag-pill.tag-outline 
      {:key tag} tag])]]])

(defn articles [{:keys [articles]}]
  (if-not (seq articles)
    [:div.artile-preview "loading"]
    (if (= 0 (count articles))
      [:div.artile-preview "No artiles found"]
      [:div
       (for [article articles]
         ^{:key (:slug article)} 
         [article-preview article])])))

(defn mainView []
  [:div.col-md-9>div.feed-toogle
   [:ul.nav.nav-pills.outline-active
    [:li.nav-item
     [:a.nav-link.active
      {:href ""} "Global Feed"]]]
   [articles @articles-state]])


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
  (browse-artices)
  (render))


(comment
  (browse-artices) 
  (deref articles-state) 
  )