(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-global-middlewares]]
            [microclj.env :as env]
            [microclj.config :as config]
            [clojure.tools.logging :refer [error info]])
  (:gen-class :main true))

(defn init []
  (env/init))

(def app (wrap-global-middlewares config/all-routes))

(defn -main [& _]
  (init)
  (run-jetty app {:port 3000}))