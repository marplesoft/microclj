(ns microclj.middleware
  (:require [clojure.tools.logging :refer [error]]))
    
(defn add-locals [handler]
  (fn [request]
    (let [context (::context request)
          response (handler request)]
      (assoc response ::locals context))))

(defn trace-and-handle-errors [handler] 
  (fn [request]
    (let [trace-id (.toString (java.util.UUID/randomUUID))
          context {:trace-id trace-id}
          request-with-context (assoc request ::context context)]
      (try
        (handler request-with-context)
        (catch Exception e
          (error (str "{" trace-id "} " (.getMessage e)))
          {:status 500
           :body (str "Unexpected error (trace-id: " trace-id ")")})))))

(defn wrap-middlewares [handler]
  (-> handler
      add-locals
      trace-and-handle-errors))