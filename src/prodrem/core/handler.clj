(ns prodrem.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [prodrem.core.routes.prodrem-routes :refer [prodrem-routes]]
            [prodrem.core.models.query-defs :as query]
            [overtone.at-at :refer :all]))
            ;;[ring.util.response :as response]
            ;;[tentacles.repos :as repos]))

(defn init
  []
  (query/create-users-table-if-not-exists!))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes prodrem-routes app-routes)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
