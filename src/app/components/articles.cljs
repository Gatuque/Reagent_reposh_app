(ns app.components.articles)

(defn article-preview [{:keys [title description author createdAt
                               favoritesCount tagList]}]
  [:div.artile-preview
   [:div.article-meta {:style {:padding "5px 2px 2px 2px"}}
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