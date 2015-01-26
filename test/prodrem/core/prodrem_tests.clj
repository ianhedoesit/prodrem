(ns prodrem.core.prodrem-tests
  (:use midje.sweet)
  (require [clojure.test :refer :all]
           [ring.mock.request :as mock]
           [prodrem.core.handler :refer :all]
           [prodrem.core.models.query-defs :as query]))

(facts "Example GET and POST tests"
       (with-state-changes [(before :facts
                                    (query/create-users-table-if-not-exists!))
                            (after :facts (query/drop-users-table!))])

       (fact "Test POST /start/"
             (let [response (app (mock/request :post "/start"
                                               {:username "test_user"
                                                :email "test@email.com"
                                                :accountname "test_account"}))]
               (:status response) => 200
               (:body response) => (contains "test_user"))))
