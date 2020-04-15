(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-middlewares]]
            [microclj.routes :refer [site]])
  (:gen-class :main true))

(def app (wrap-middlewares site))

(defn -main [& _]
  (run-jetty app {:port 3000}))

(comment
  (-main))