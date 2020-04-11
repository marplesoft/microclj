(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-middlewares]]
            [microclj.routes :refer [site]])
  (:gen-class :main true))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello World " (:context request))})

(def app (wrap-middlewares site))

(defn -main [& _]
  (run-jetty app {:port 3000}))

(comment
  (-main))