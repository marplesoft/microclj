(ns microclj.first.home
  (:require [microclj.rdb :refer [query]]
            [microclj.rdb :refer [query]]
            [microclj.first.common :as common]
            [hiccup.core :refer [html]]
            [hiccup.util :refer [to-uri]]))

(defn get [req]
  (let [videosWatched (->
                       (query ["select sum(view_count) as videoswatched from videos"])
                       first
                       :videoswatched)]
    (common/layout-template
     (html
      [:h1 "This is the home page"]
      [:p (format "Viewers have watched %s videos" (or videosWatched 0))]
      [:form {:action (to-uri "/record-viewing/12345") :method "POST"}
       [:button {:type "submit"} "Record viewing video"]]))))