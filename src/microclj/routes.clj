(ns microclj.routes
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]))

(defn index-page [request]
  (str "Hello to " (:context request)))

(defroutes site-routes
  (GET "/" request (index-page request)))

(def site (handler/site site-routes))