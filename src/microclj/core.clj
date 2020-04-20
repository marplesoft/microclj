(ns microclj.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [microclj.middleware :refer [wrap-middlewares]]
            [microclj.routes :refer [site]]
            [microclj.env :refer [check-for-missing-env get-env]]
            [clojure.tools.logging :refer [error info]]
            [clojure.string :refer [join]])
  (:gen-class :main true))

(defn init []
  (let [missing-vars (check-for-missing-env)]
    (when (not-empty missing-vars)
      (error (str "Required environment vars missing: " (join "," missing-vars)))
      (System/exit -1)))
  (info (format "Starting microclj in '%s' mode" (get-env :mode))))

(def app (wrap-middlewares site))

(defn -main [& _]
  (init)
  (run-jetty app {:port 3000}))