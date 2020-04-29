(ns microclj.first.home
  (:require [microclj.rdb :refer [query-one]]
            [microclj.first.common :as common]
            [compojure.core :refer [defroutes GET]]
            [hiccup.core :refer [html]]
            [hiccup.util :refer [to-uri]]))

(defn home-page-template [{:keys [videos-watched]}]
  (common/layout-template
   (html
    [:h1 "This is the home page"]
    [:p (format "Viewers have watched %s videos" (or videos-watched 0))]
    [:form {:action (to-uri "/record-viewing/12345") :method "POST"}
     [:button {:type "submit"} "Record viewing video"]])))

(defn get-home [req]
  (let [videos-watched (:videoswatched (query-one ["select sum(view_count) as videoswatched from videos"]))]
    (home-page-template {:videos-watched videos-watched})))

(defroutes routes
  (GET "/" req (get-home req)))