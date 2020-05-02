(ns example.app
  (:require [mount.core :as mount]
            [example.runner :refer [runner]]))

(defn -main [& _]
  (mount/start)
  (runner))

(comment
  (-main))