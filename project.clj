(defproject microclj "0.1"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [mount "0.1.16"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [compojure "1.6.1"]
                 [org.clojure/tools.logging "1.0.0"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [org.slf4j/slf4j-log4j12 "1.7.30"]
                 [environ "1.1.0"]
                 [org.postgresql/postgresql "42.2.12"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [ragtime "0.8.0"]
                 [hiccup "1.0.5"]]
  :dev-dependencies [[ring/ring-devel "1.8.0"]]
  :aot [microclj.core]
  :main microclj.core
  :repl-options {:init-ns dev}
  )