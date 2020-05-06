(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [mount.core :refer [defstate start]]
            [microclj.env]
            [microclj.rdb]
            [microclj.config :as config]
            [microclj.middleware :refer [wrap-global-middlewares]]
            [clojure.tools.logging :refer [error info]])
  (:gen-class :main true))

(defn make-app [] (wrap-global-middlewares config/all-routes))

(defstate app :start (make-app))

(defn- start-jetty [app]
  (run-jetty app {:port 3000
                  :join? false}))

(defstate webserver :start (start-jetty app))

(defn -main [& _]
  (println "Mounted: " (start)))