(ns microclj.config
  (:require [microclj.first.app :as first]
            [compojure.core :refer [routes]]))

(def all-routes
  (routes
   first/app))