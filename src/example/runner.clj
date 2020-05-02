(ns example.runner
  (:require [mount.core :refer [defstate]]))

(defn- get-runner []
  (fn [] (println "running")))

(defstate runner :start (get-runner))