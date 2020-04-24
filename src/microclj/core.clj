(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-middlewares]]
            [microclj.env :as env]
            [microclj.config :as config]
            [clojure.tools.logging :refer [error info]])
  (:gen-class :main true))

(defn init []
  (env/init))

(def app (wrap-middlewares config/all-routes))

(defn -main [& _]
  (init)
  (run-jetty app {:port 3000}))