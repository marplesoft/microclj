(def handler "ryan")
(def others '(clojure.string/upper-case clojure.string/reverse))
(apply -> (cons handler others))

(.toString (java.util.UUID/randomUUID))

(use 'microclj.middleware)
(defn handler [req]
  {:status 200
   :body "hi"})
(handler {})

(defn bad-handler [req]
  (/ 1 0))

(def m1 (add-locals handler))
(def m1 (add-locals bad-handler))
(m1 {})
(def m2 (add-request-context m1))
(m2 {}) 
(def m3 (last-resort-error-handler m2))
(m3 {})

((last-resort-error-handler (fn [req] (/ 1 0))) {})

 ((-> bad-handler
      add-locals
      add-request-context
      last-resort-error-handler) {})
