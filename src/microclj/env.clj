(ns microclj.env
  (:require [environ.core :as environ]
            [clojure.string :as str]
            [clojure.tools.logging :refer [info error]]
            [clojure.string :refer [join]]))

(defn get-env [name]
  (environ/env name))

(defn missing-vars []
  (for [required-var [:mode :rdb-url]
        :let [value (get-env required-var)]
        :when (nil? value)]
    required-var))

(defn init []
  (let [missing (missing-vars)]
    (when (not-empty missing)
      (error (str "Required environment vars missing: " (join ", " missing)))
      (System/exit -1)))
  (info (format "Clojure Microservices App started in '%s' mode" (get-env :mode)))
  (info (format "Using RDB-URL '%s'" (get-env :rdb-url))))