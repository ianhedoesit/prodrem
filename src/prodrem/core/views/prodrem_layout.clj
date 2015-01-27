(ns prodrem.core.views.prodrem-layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html h]]
            [overtone.at-at :refer [now]]))

(comment
  I think it might be better to have something like `header-layout`,
  `footer-layout`, `navbar-layout`, etc. since I don't know what a
  `common-layout` would really entail in a project like this.)

(comment inspired by overtone.at-at.core/format-date)
(defn- format-date
  "Format date object as a string such as: 'Mon 15:23:35'"
  [date]
  (.format (java.text.SimpleDateFormat. "EEE hh':'mm':'ss") date))

(defn common-layout
  [& body]
  (html5
    [:head
     [:title "Productivity Reminder"]
     (include-css "/css/prodrem.css")
     (comment this should also include bootstrap stuff)]
    [:body
     [:div#temp
      body]]))

(defn add-user-form
  []
  (html
    [:p#test
     (h "user-form test")]))

(defn read-user
  [user]
  (html
    [:div.user
     [:div.user-text
      [:div.column-1 (h (:username user))]
      [:div.column-2 (h (:email user))]
      [:div.column-3 (h (:accountname user))]]
    [:div.clear-row]]))

(defn edit-user
  [user]
  "")

(defn reminder-layout
  [user]
  (html
    [:div.contact
     (h (str user "'s last commit was 24 hours ago as of " (format-date (now)) "!"))]))
