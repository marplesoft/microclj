(defproject microclj "0.1"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [compojure "1.6.1"]
                 [org.clojure/tools.logging "1.0.0"]
                 [org.slf4j/slf4j-log4j12 "1.7.30"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [environ "1.1.0"]]
  :dev-dependencies [[ring/ring-devel "1.8.0"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-environ "1.1.0"]]
  :ring {:handler microclj.core/app}
  :profiles {:dev {:env {:mode "dev"}}}
  :main microclj.core
  :aot [microclj.core])