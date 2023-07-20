(ns app.auth)

(defn auth-signin [event]
  (.preventDefault event)
  (js/console.log "LOGIN"))

(defn auth-register [event]
  (.preventDefault event)
  (js/console.log "REGISTER"))