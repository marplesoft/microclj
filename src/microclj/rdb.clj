(ns microclj.rdb
  (:require [microclj.env :refer [get-env]]
            [clojure.java.jdbc :as jdbc]))

(def db-conn-url (format "%s&password=%s" (get-env :rdb-url) (get-env :rdb-pass)))

(defn insert! [args] (apply jdbc/insert! db-conn-url args))
(defn update! [args] (apply jdbc/update! db-conn-url args))
(defn delete! [args] (apply jdbc/delete! db-conn-url args))
(defn query [args] (apply jdbc/query db-conn-url args))