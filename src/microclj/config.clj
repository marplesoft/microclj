(ns microclj.config
  (:require [microclj.first.app :as first]
            [compojure.core :refer [routes context]]
            [compojure.route :as route]))

(def all-routes
  (routes
   (context "/first" [] first/app-routes)
   (route/not-found "<h1>Page not found</h1>"))) 