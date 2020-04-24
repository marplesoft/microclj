(ns microclj.first.app
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn index-page [request] 
  (str "Trace ID: " (get-in request [:microclj.middleware/context :trace-id])))

(defn throw-error [_]
  (/ 1 0))

(defroutes app
  (GET "/trace" request (index-page request))
  (GET "/err" request (throw-error request))
  (route/resources "/"))


