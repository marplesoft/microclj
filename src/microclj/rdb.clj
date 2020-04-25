(ns microclj.rdb
  (:require [microclj.env :refer [get-env]]
            [clojure.java.jdbc :as jdbc]
            [microclj.config :as config]
            [ragtime.repl :as rag]
            [ragtime.jdbc :as rag.jdbc]
            [clojure.tools.logging :refer [info]]))

(def db-spec {:dbtype "postgresql"
              :dbname "microclj"
              :host "localhost"
              :port 5432
              :user "app"
              :password (get-env :rdb-pass)})

(defn insert! [args] (apply jdbc/insert! db-spec args))
(defn update! [args] (apply jdbc/update! db-spec args))
(defn delete! [args] (apply jdbc/delete! db-spec args))
(defn query [args] (apply jdbc/query db-spec args))

(defn log-migration-step [migration-folder _ op id]
  (let [action (case op :up "Applying" :down "Rolling back")]
    (info (format "%s migration %s to %s" action id migration-folder))))

(defn run-migration [migration-folder]
  (info (format "Checking for migrations in %s ..." migration-folder))
  (let [config {:datastore (rag.jdbc/sql-database db-spec {:migrations-table "migrations"})
                :migrations (rag.jdbc/load-resources (str "migrations/" migration-folder))
                :reporter (partial log-migration-step migration-folder)}]
    (rag/migrate config)))

(defn run-migrations []
  (doseq [migration-folder config/migration-folders]
    (run-migration migration-folder)))

(defn init []
  (run-migrations))