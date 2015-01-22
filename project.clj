(defproject prodrem "0.1.0-SNAPSHOT"
  :description "Productivity reminder based on git commits."
  :url "http://ianhedoesit.com/prodrem"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [tentacles "0.3.0"]
                 [hiccup "1.0.5"]
                 [com.draines/postal "1.11.3"]
                 [overtone/at-at "1.2.0"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler prodrem.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
