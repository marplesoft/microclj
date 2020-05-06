(ns microclj.rdb
  (:require [mount.core :refer [defstate]] 
            [microclj.env :refer [env]]
            [clojure.java.jdbc :as jdbc]
            [ragtime.repl :as rag]
            [ragtime.jdbc :as rag.jdbc]
            [clojure.tools.logging :refer [info]]))

(defn dynamic-db-spec []
  (info (format "Using RDB host '%s' - %s"
                (env :rdb-host)
                (if (env :rdb-pass) "password provided" "no password provided")))
  (let [base-spec {:dbtype "postgresql"
                   :dbname "microclj"
                   :host (env :rdb-host)
                   :port 5432
                   :user "app"}]
    (if-let [rdb-pass (env :rdb-pass)]
      (assoc base-spec :password rdb-pass)
      base-spec)))

(defstate db-spec :start (dynamic-db-spec))

(defn log-migration-step [migration-folder _ op id]
  (let [action (case op :up "Applying" :down "Rolling back")]
    (info (format "%s migration %s to %s" action id migration-folder))))

(defn run-migration [migration-folder]
  (info (format "Checking for migrations in %s ..." migration-folder))
  (let [config {:datastore (rag.jdbc/sql-database db-spec {:migrations-table "migrations"})
                :migrations (rag.jdbc/load-resources (str "migrations/" migration-folder))
                :reporter (partial log-migration-step migration-folder)}]
    (rag/migrate config)))

(def migration-folders
  ["first"])

(defn run-migrations []
  (doseq [migration-folder migration-folders]
    (run-migration migration-folder)))

(defstate db-migrations :start (run-migrations))

(defn insert! [args] (apply jdbc/insert! db-spec args))
(defn update! [args] (apply jdbc/update! db-spec args))
(defn delete! [args] (apply jdbc/delete! db-spec args))
(defn query [args] (apply jdbc/query db-spec args))
(defn query-one [args] (first (query args)))