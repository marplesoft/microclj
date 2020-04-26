(ns microclj.first.app
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.page :refer [html5 include-css]]
            [hiccup.middleware :refer [wrap-base-url]]))

(defn trace-page [request] 
  (str "trace-id: " (get-in request [:microclj.middleware/context :trace-id])))

(defn throw-error [_]
  (/ 1 0))

(defn home [req]
  (html5
   [:head
    [:title "Video Tutorials"]
    (include-css "/css/fonts.css")
    (include-css "/css/bootstrap.min.css")]
   [:body
    [:h1 "Home"]]))

(defroutes app-routes-raw
  (GET "/" req (home req))
  (GET "/trace" request (trace-page request))
  (GET "/err" request (throw-error request))
  (route/resources "/" {:root "public/first"}))

(def app-routes (wrap-base-url app-routes-raw))