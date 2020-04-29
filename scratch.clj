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
     (map #(hash-map % (microclj.env/get-env %)))
     (apply merge))

(hash-map :a 1)
(interleave [:a :b :c] [nil])

(use '[compojure.core])

(clojure.walk/macroexpand-all '(defroutes my-routes
               (GET "/foo" request (handler request))))

(defn app-routes []
  (routes
   (GET "/foo" request (handler request))))

(clojure.walk/macroexpand-all '(app-routes))

(clojure.walk/macroexpand-all '(defroutes all-routes
                                 (app-routes)))

(defroutes routes1
  (GET "/1" req (handler req)))

(defroutes routes2
  (GET "/2" req (handler req))
  (GET "/3" req (handler req)))

(routes2 {:uri "/2"})

(def app
  (routes
   routes1
   routes2))

(app {:uri "/2"})

(def both-routes (concat routes1 routes2))
(macroexpand-1 `(defroutes all-routes
                 (all-routes)))  
(defroutes all-the-routes
  all-routes)

(app {:server-port 80
             :server-name "127.0.0.1"
             :remote-addr "127.0.0.1"
             :uri "/1"
             :scheme :http
             :headers {}
             :request-method :get})

(defn domath [f x y]
  (f x y))
(domath - 7 3)

(defn add [x y]
  `(domath + x y))
(add 3 4)

(macroexpand-1 `(GET "/" req (handler req)))

(microclj.config/app {:server-port 80
      :server-name "127.0.0.1"
      :remote-addr "127.0.0.1"
      :uri "/first/"
      :scheme :http
      :headers {}
      :request-method :get})

(defn req [uri] 
  {:server-port 80
   :server-name "127.0.0.1"
   :remote-addr "127.0.0.1"
   :uri uri
   :scheme :http
   :headers {}
   :request-method :get})

(microclj.first.app/app (req "/"))

(require '[clojure.java.jdbc :as sql])
(def db "postgresql://localhost:5432/microclj?user=app&password=notsecretfordev")
(sql/db-do-commands db 
                    (sql/create-table-ddl :test [[:name :text]]))
(sql/insert! db :test {:name "ryan"})
(sql/insert! db :test {:name "kate"})
(sql/insert! db :test {:name "lily"})
(sql/query db ["select name from test"])

(def config {:datastore (ragtime.jdbc/sql-database {:connection-uri db-conn-url} {:migrations-table "migrations"})
             :migrations (load-resources (str "migrations/" migration-folder))})
(ragtime.core/applied-migrations)

(in-ns 'microclj.rdb)
(Class/forName "org.postgresql.Driver")
(microclj.rdb/run-migration "first")

(-> (microclj.rdb/query ["select sum (view_count) as videoswatched from videos"])
    first
    :videoswatched)

(microclj.first.app/home {})

(def x 32)
*ns*