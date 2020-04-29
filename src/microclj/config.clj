(ns microclj.config
  (:require [microclj.first.app :as first]
            [compojure.core :refer [routes context]]))

(def all-routes
  (routes
   (context "/first" [] first/app-routes))) 