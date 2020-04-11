(ns microclj.middleware)

;; just an example
(defn add-request-context [handler]
  (fn [request]
    (handler (assoc request :context (.toString (java.util.UUID/randomUUID))))))

(defn wrap-middlewares [handler]
  (-> handler
      add-request-context))


