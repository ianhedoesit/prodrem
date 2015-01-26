(ns prodrem.core.views.prodrem-layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html h]]))

(comment
  I think it might be better to have something like `header-layout`,
  `footer-layout`, `navbar-layout`, etc. since I don't know what a
  `common-layout` would really entail in a project like this.)

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
  "")

(defn read-user
  [user]
  "")

(defn edit-user
  [user]
  "")
