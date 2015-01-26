(ns prodrem.core.routes.prodrem-routes
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [prodrem.core.views.prodrem-layout :refer [common-layout
                                                       read-user
                                                       add-user-form
                                                       edit-user]]
            [prodrem.core.models.query-defs :as query]))

(defn display-user
  [user user-id]
  (if (not= (and user-id (Integer. user-id)) (:id user))
    (read-user user)
    (edit-user user)))

(defn start-route
  "The start route adds a user to be monitored.
  `POST /start/:u` will email the user after 24 hours from this POST."
  [request]
  (let [username (get-in request [:params :username])
        email (get-in request [:params :email])
        accountname (get-in request [:params :accountname])]
    (prn "user: " username "; email: " email "; (GH) account: " accountname)
    (query/insert-user<! {:username username :email email
                            :accountname accountname})
    (comment get most recent commit here)
    (comment set new reminder 24 hours from now)
    (str "user: " username "; email: " email "; (GH) account: " accountname \newline)))

(defn get-route
  [request]
  "")

(defn delete-route
  [request]
  "")

(defn update-route
  [request]
  "")

(defroutes prodrem-routes
  (GET "/" [] "hello")
  (POST "/start" [] start-route)
  (GET "/edit/:user-id" [] get-route)
  (POST "/edit/:user-id" [] update-route)
  (POST "/delete/:user-id" [] delete-route))

