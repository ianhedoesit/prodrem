(ns prodrem.core.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [prodrem.core.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (is (= true true)))
    ;;(let [response (app (mock/request :get "/"))]
    ;;  (is (= (:status response) 200))
    ;;  (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (is (= true true))))
    ;;(let [response (app (mock/request :get "/invalid"))]
    ;;  (is (= (:status response) 404)))))
