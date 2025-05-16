(ns dawn-of-a-legend.json-placeholder.get-users
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [clojure.java.io :as io]))

(def response (client/get "https://jsonplaceholder.typicode.com/users"))
(def response-body (get-in response [:body]))
(def response-keyword (json/parse-string response-body true))
(def response-even (filter #(even? (:id %)) response-keyword))

(defn create-user-info [users]
  {:userInformations (map
                      (fn [user]
                        {:id (:id user)
                         :name (:name user)
                         :address (hash-map :city (get-in user [:address :city]))
                         :phone-number (:phone user)}) users)})
(def user-info (create-user-info response-even))
(comment
  (with-open [w (io/writer "user-info.json")]
    (json/generate-stream user-info w {:pretty true})))