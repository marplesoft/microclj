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
      last-resort-error-handler) {:foo "bar"})

((-> bad-handler
     add-request-context
     show-req) {})

(defn show-req [handler]
  (fn [req]
    (handler req)
    (clojure.pprint/pprint req)))

(def testHandler (microclj.middleware/wrap-middlewares bad-handler))
(testHandler {})

(microclj.env/get-env :foo)

(mapcat microclj.env/get-env [:foo :foo2])
(concat "bar")
(microclj.env/get-env :foo)

(map identity [1 2 3])
(apply map identity [1 2 3])

(for [n [1 nil 3]]
  n)

(microclj.env/check-for-missing-env)
(microclj.core.-main)

(-> [:abc :xyz] clojure.string/upper-case)

(mapcat #(clojure.string/reverse %) ["abc" "xyz"])

(microclj.env/init)
(map (fn [key] {key (microclj.env/get-env key)}) [:mode :missing])

(->> [:mode :missing]
    (map #(hash-map % (microclj.env/get-env %))))

(hash-map :a 1)
(interleave [:a :b :c] [nil])

