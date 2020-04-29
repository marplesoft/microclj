(ns microclj.first.app
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [hiccup.middleware :refer [wrap-base-url]]
            [microclj.first.home :as home]))

(defroutes app-routes-raw
  (GET "/" req (home/get req))
  (route/resources "/" {:root "public/first"}))

(def app-routes (wrap-base-url app-routes-raw))