(ns microclj.first.app
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.page :refer [html5 include-css]]
            [hiccup.middleware :refer [wrap-base-url]]
            [hiccup.util :refer [with-base-url]]))

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
    [:nav.navbar.navbar-expand-lg.navbar-light.bglight
     [:a.navbar-brand {:href "#"} "Video Tutorials"]
     [:button.navbar-toggler {:type "button" :data-toggle= "collapse" :data-target "#navbarSupportedContent"}
      [:span.navbar-toggler-icon]]
     [:div.collapse.navbar-collapse
      [:ul.navbar-nav.mr-auto
       [:li.nav-item
        [:a.nav-link {:href (str "/")} "Home"]]
       [:li.nav-item
        [:a.nav-link {:href "/creators-portal"} "Creator's Portal"]]]]]]))

(defroutes app-routes-raw
  (GET "/" req (home req))
  (GET "/trace" request (trace-page request))
  (GET "/err" request (throw-error request))
  (route/resources "/" {:root "public/first"}))

(def app-routes (wrap-base-url app-routes-raw))