(ns microclj.middleware)

;; just an example
(defn add-request-context [handler]
  (fn [request]
    (let [context {:trace-id (.toString (java.util.UUID/randomUUID))}]
      (handler (assoc request :context context)))))

(defn wrap-middlewares [handler]
  (-> handler
      add-request-context))


