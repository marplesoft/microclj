(ns microclj.first.common
  (:require  [hiccup.page :refer [html5 include-css include-js]]))

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
    (include-js "/js/bootstrap.bundle.min.js")]))