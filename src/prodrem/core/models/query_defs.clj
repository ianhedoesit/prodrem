(ns prodrem.core.models.query-defs
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

(defqueries "prodrem/core/models/prodrem_queries.sql"
            {:connection (env :database-url)})
