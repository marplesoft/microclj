(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-middlewares]]
            [microclj.routes :refer [site]]
            [microclj.env :refer [check-for-missing-env]]
            [clojure.tools.logging :refer [error]]
            [clojure.string :refer [join]])
  (:gen-class :main true))

(def app (wrap-middlewares site))

(defn -main [& _]
  (let [missing-vars (check-for-missing-env)]
    (when (not-empty missing-vars)
      (error (str "Required environment vars missing: " (join "," missing-vars)))
      (System/exit -1)))
  (run-jetty app {:port 3000}))

(comment
  (-main))