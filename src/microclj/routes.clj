(ns microclj.routes
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn index-page [request]
  (str "Trace ID: " (get-in request [:context :trace-id])))

(defn throw-error [_]
  (/ 1 0))

(defroutes site-routes
  (GET "/" request (index-page request))
  (GET "/err" request (throw-error request))
  (route/resources "/"))

(def site (handler/site site-routes))