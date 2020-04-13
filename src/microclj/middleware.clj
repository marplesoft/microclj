(ns microclj.middleware)

(defn add-request-context [handler]
  (fn [request]
    (let [context {:trace-id (.toString (java.util.UUID/randomUUID))}]
      (handler (assoc request :context context)))))
      
(defn add-locals [handler]
  (fn [request]
    (let [context (:context request)
          response (handler request)]
      (assoc response :locals context))))

(defn last-resort-error-handler [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (println (str "ERROR CAUGHT: " (.getMessage e)))))))

(defn wrap-middlewares [handler]
  (-> handler
      add-locals
      add-request-context))
