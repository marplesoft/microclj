(ns microclj.first.app
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn trace-page [request] 
  (str "trace-id: " (get-in request [:microclj.middleware/context :trace-id])))

(defn throw-error [_]
  (/ 1 0))

(defroutes app-routes
  (GET "/trace" request (trace-page request))
  (GET "/err" request (throw-error request))
  (route/resources "/" {:root "public/first"}))
