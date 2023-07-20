(ns app.components.banner)

(defn banner [token]
  (when token
    [:div.banner>div.container
     [:h1.logo-front "Reagent Reposh"]
     [:p "Best Framework to create easy SPA site"]]))