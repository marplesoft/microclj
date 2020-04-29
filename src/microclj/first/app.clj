(ns microclj.first.app
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html5 include-css include-js ]]
            [hiccup.middleware :refer [wrap-base-url]]
            [hiccup.util :refer [with-base-url]]
            [microclj.rdb :refer [query]]))

(defn trace-page [request] 
  (str "trace-id: " (get-in request [:microclj.middleware/context :trace-id])))

(defn throw-error [_]
  (/ 1 0))

(defn layout-template [content]
  (html5
   [:head
    [:title "Video Tutorials"]
    (include-css "/css/fonts.css")
    (include-css "/css/bootstrap.min.css")]
   [:body
    [:nav.navbar.navbar-expand-lg.navbar-light.bglight
     [:a.navbar-brand {:href "#"} "Video Tutorials"]
     [:button.navbar-toggler {:type "button" :data-toggle= "collapse" :data-target "#navbarSupportedContent"}
      [:span.navbar-toggler-icon]]
     [:div.collapse.navbar-collapse
      [:ul.navbar-nav.mr-auto
       [:li.nav-item
        [:a.nav-link {:href (str "/")} "Home"]]
       [:li.nav-item
        [:a.nav-link {:href "/creators-portal"} "Creator's Portal"]]]
      [:ul.navbar-nav
       [:li.nav-item
        [:a.nav-link {:href "/register"} "Register"]]
       [:li.nav-item
        [:a.nav-link {:href "/auth/log-in"} "Log In"]]]]]
    [:div.container
     [:div {:style "margin-top: 30px"}
      content]]
    (include-js "/js/jquery-3.2.1.slim.min.js")
    (include-js "/js/popper.min.js")
    (include-js "/js/bootstrap.bundle.min.js")
    ]))

(defn home [req]
  (let [videosWatched (-> 
                       (query ["select sum(view_count) as videoswatched from videos"])
                       first
                       :videoswatched)]
    (layout-template
     (html
      [:h1 "This is the home page"]
      [:p (format "Viewers have watched %s videos" (or videosWatched 0))]
      [:form {:action "/record-viewing/12345" :method "POST"}
       [:button {:type "submit"} "Record viewing video"]]))))

(defroutes app-routes-raw
  (GET "/" req (home req))
  (GET "/trace" request (trace-page request))
  (GET "/err" request (throw-error request))
  (route/resources "/" {:root "public/first"}))

(def app-routes (wrap-base-url app-routes-raw))