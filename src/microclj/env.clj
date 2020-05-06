(ns microclj.env
  (:require [mount.core :refer [defstate]]
            [environ.core :as environ]
            [clojure.string :as str]
            [clojure.tools.logging :refer [info error]]
            [clojure.string :refer [join]]))

(defn- get-env [name]
  (environ/env name))

(defn- missing-vars []
  (for [required-var [:mode :rdb-host]
        :let [value (get-env required-var)]
        :when (nil? value)]
    required-var))

(defn- start []
  (let [missing (missing-vars)]
    (when (not-empty missing)
      (error (str "Required environment vars missing: " (join ", " missing)))
      (System/exit -1)))
  (info (format "Clojure Microservices App started in '%s' mode" (get-env :mode)))
  get-env)

(defstate env :start (start))