(ns prodrem.core.routes.prodrem-routes
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [prodrem.core.views.prodrem-layout :refer [common-layout
                                                       read-user
                                                       add-user-form
                                                       edit-user
                                                       reminder-layout]]
            [prodrem.core.models.query-defs :as query]
            [overtone.at-at :refer :all]
           ;; [cheshire.core :refer [parse-string decode]]
            [clj-http.lite.client :as client]
            [hiccup.util :refer [url-encode]]
            [environ.core :refer [env]]))

(def pr-pool (mk-pool))

(def hdrs {"User-Agent" "ProdRem (gh/ianhedoesit/prodrem)"
           "Content-Type" "application/x-www-form-urlencoded"})
(def endpoint "https://rest.nexmo.com/sms/json")
(def api {:key (env :api-key)
          :secret (env :api-secret)
          :number (env :api-number)})

(defn send-to
  [num mess]
  (def uri
    (str endpoint \? "api_key=" (env :api-key) \&
         "api_secret=" (env :api-secret) \&
         "from=" (env :api-number) \&
         "to=" num \&
         "text=" (url-encode mess)))
  (client/get uri))

(defn display-user
  [user user-id]
  (if (not= (and user-id (Integer. user-id)) (:id user))
    (read-user user)
    (edit-user user)))

(defn- send-reminder
  "Send's the reminder to `username` that they have not made any git commits in
  the last 24 hours."
  [username]
  (comment for now this will just update the root index page saying
           "at (time), it has been 24 hours since `username` has made a git
           commit")
  (send-to (env :to-number)
           (str "It's been 24 hours since your last GitHub commit! Get committing! :)")))

(defn start-route
  "The start route adds a user to be monitored.
  `POST /start/:u` will email the user after 24 hours from this POST."
  [request]
  (let [username (get-in request [:params :username])
        email (get-in request [:params :email])
        accountname (get-in request [:params :accountname])]
    (query/insert-user<! {:username username :email email
                            :accountname accountname})
    (comment get most recent commit here)
    (comment set new reminder 24 hours from now)
    (every (* 5 60 1000) #(send-reminder username) pr-pool)
    ;;(send-reminder username)
    ))

(defn test-route
  "FOR TESTING PURPOSES ONLY DO NOT USE"
  [request]
  (let [number (get-in request [:params :number])
        message (get-in request [:params :message])]
     (at (+ (* 2 60 1000) (now))  #(send-to number message) pr-pool)))

(defn get-route
  [request]
  "")

(defn delete-route
  [request]
  "")

(defn update-route
  [request]
  "")

(defn home-route
  [request]
  (let [user-id (get-in request [:params :user-id])]
    (common-layout
      (for [user (query/all-users)] ;; simply iterate over all the users
        (display-user user user-id)) ;; display each
      (add-user-form)))) ;; add-user-form would be the input area; at bottom

(defn info-route
  [request]
  (let [jobs (scheduled-jobs pr-pool)]
    (if (empty? jobs)
      (str "No jobs are currently scheduled.")
      (str "There are currently " (count jobs) " jobs" \newline))))

(defroutes prodrem-routes
  (GET "/" [] home-route)
  (POST "/start" [] start-route)
  (GET "/edit/:user-id" [] get-route)
  (POST "/edit/:user-id" [] update-route)
  (POST "/delete/:user-id" [] delete-route)
  (GET "/info" [] info-route)
  (POST "/test" [] test-route))

