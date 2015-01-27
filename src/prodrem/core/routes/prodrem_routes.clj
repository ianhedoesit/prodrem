(ns prodrem.core.routes.prodrem-routes
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [prodrem.core.views.prodrem-layout :refer [common-layout
                                                       read-user
                                                       add-user-form
                                                       edit-user
                                                       reminder-layout]]
            [prodrem.core.models.query-defs :as query]
            [overtone.at-at :refer :all]))

(def pr-pool (mk-pool))

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
  (common-layout (reminder-layout username)))

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
    (every (* 1 60 1000) #(send-reminder username) pr-pool)
    (send-reminder username)))

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
  (GET "/info" [] info-route))

