(ns dawn-of-a-legend.json-placeholder.get-posts
  (:require 
   [clj-http.client :as client]
   [cheshire.core :as core]))

(def json-body (:body (client/get "https://jsonplaceholder.typicode.com/posts")))
(def keyword-body (core/parse-string json-body true))
(defn get-posts-by-user-id [user-id]
  (filter #(= (:userId %) user-id) keyword-body))

(comment 
  (get-posts-by-user-id 1)
  )