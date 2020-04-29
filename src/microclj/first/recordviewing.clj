(ns microclj.first.recordviewing
  (:require [compojure.core :refer [defroutes POST]]
            [clojure.tools.logging :refer [info]]))

(defn post [req id]
  (info (format "record viewing for video %s" id))
  "ok")

(defroutes routes
  (POST "/:id" [req id] (post req id)))