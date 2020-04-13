(def handler "ryan")
(def others '(clojure.string/upper-case clojure.string/reverse))
(apply -> (cons handler others))

(.toString (java.util.UUID/randomUUID))

(use '[clojure.repl :only (source)])

(use 'microclj.middleware)
(defn handler [req]
  {:status 200
   :body "hi"})
(handler {})

(def m1 (add-locals handler))
(m1 {})
(def m2 (add-request-context m1))
(m2 {}) 
