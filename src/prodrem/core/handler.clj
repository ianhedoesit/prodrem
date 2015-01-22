(ns prodrem.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response]
            [tentacles.repos :as repos]))

(defn post-user-route
  "Expects a username"
  [request]
  (let [user (get-in request [:params :user])]
    ;;(query/insert-contact<! {:user user})
    ;;(repos)
    (response/redirect "/")))

(defroutes app-routes
  (POST "/user" [] post-user-route)
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
