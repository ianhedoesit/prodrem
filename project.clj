(defproject prodrem "0.1.0-SNAPSHOT"
  :description "Productivity reminder based on git commits."
  :url "http://ianhedoesit.com/prodrem"
  :min-lein-version "2.0.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [tentacles "0.3.0"]
                 [hiccup "1.0.5"]
                 [im.chit/cronj "1.4.3"]
                 [overtone/at-at "1.2.0"]
                 [yesql "0.5.0-rc1"]
                 [environ "1.0.0"]
                 [postgresql/postgresql "9.3-1102.jdbc41"]
                 [org.clojure/java.jdbc "0.3.6"]]

  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.0"]]
  
  :ring {:handler prodrem.core.handler/app
         :init prodrem.core.handler/init}
  
  :profiles {:test-local {:dependencies [[midje "1.6.3"]
                                         [javax.servlet/servlet-api "2.5"]
                                         [ring-mock "0.1.5"]]
                          :plugins [[lein-midje "3.1.3"]]}
             
             :test-env-vars {}
             :dev-env-vars {}
             
             :test [:test-local :test-env-vars]
             :dev [:dev-env-vars]
             
             :production {:ring {:open-browser? false
                                 :stacktraces? false
                                 :auto-reload? false}}})
