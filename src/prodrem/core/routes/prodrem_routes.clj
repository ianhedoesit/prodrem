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

(defn post-route
  [request]
  "")

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
  (GET "/user/:u" [u] (str "user = " u))
  (GET "/edit/:contact-id" [] get-route)
  (POST "/edit/:contact-id" [] update-route)
  (POST "/delete/:contact-id" [] delete-route)) 
