(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-middlewares]]
            [microclj.routes :refer [site]]
            [microclj.env :as env]
            [clojure.tools.logging :refer [error info]])
  (:gen-class :main true))

(defn init []
  (env/init))

(def app (wrap-middlewares site))

(defn -main [& _]
  (init)
  (run-jetty app {:port 3000}))