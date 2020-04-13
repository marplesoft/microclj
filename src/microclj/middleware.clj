(ns microclj.middleware)

(defn add-request-context [handler]
  (let [context {:trace-id (.toString (java.util.UUID/randomUUID))}]
    (fn 
      ([request]
       (handler (assoc request :context context)))
      ([request response raise]
       (handler (assoc request :context context) response raise)))))

(defn add-locals [handler]
  (fn 
    ([request]
     (let [context (:context request)
           response (handler request)]
       (assoc response :locals context)))
    ([request response raise]
     (let [context (:context request)
           response (handler request response raise)]
       (assoc response :locals context)))))

(defn wrap-middlewares [handler]
  (-> handler
      add-locals
      add-request-context))
