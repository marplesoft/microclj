(ns microclj.env
  (:require [environ.core :as environ]
            [clojure.string :as str]))

(defn get-env [name]
  (environ/env name))

(defn check-for-missing-env []
  (for [required-var [:mode]
        :let [value (get-env required-var)]
        :when (nil? value)]
    (str/upper-case (subs (str required-var) 1))))