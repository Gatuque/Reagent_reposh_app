(ns app.components.banner)

(defn banner [token]
  (when token
    [:div.banner>div.container
     [:h1.logo-front "conduit"]
     [:p "A place to share your knowledge"]]))