(defproject microclj "0.1"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [compojure "1.6.1"]]
  :dev-dependencies [[ring/ring-devel "1.8.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler microclj.core/app}
  :main microclj.core
  :aot [microclj.core])