(ns nattoku.ch3 
  (:require
   [clojure.string :as str]))

(let [apple-book ["Apple" "Book"]
      apple-book-mango (conj apple-book "Mango")]
  (prn apple-book)
  (prn apple-book-mango))

(defn- initial-uppercase [name]
  (str/upper-case (first name)))

(defn abbreviate [name]
  (let [initial (initial-uppercase (first (str/split name #"\s")))
        last-name (last (str/split name #"\s"))] 
    (str initial ". " last-name)))

(defn first-two [list]
  (take 2 list))

(defn last-two [list]
  (take-last 2 list))

(defn moved-first-two-to-the-end [list]
  (let [first-two (first-two list)
        other (drop (count first-two) list)]
    (concat other first-two)))

