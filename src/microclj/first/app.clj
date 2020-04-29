(ns microclj.first.app
  (:require [compojure.core :refer [defroutes context]]
            [compojure.route :as route]
            [hiccup.middleware :refer [wrap-base-url]]
            [microclj.first.home :as home]
            [microclj.first.recordviewing :as recordviewing]))

(defroutes app-routes-raw
  (context "/" [] home/routes)
  (context "/record-viewing" [] recordviewing/routes)
  (route/resources "/" {:root "public/first"}))

(def app-routes (wrap-base-url app-routes-raw))