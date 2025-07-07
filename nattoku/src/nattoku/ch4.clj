(ns nattoku.ch4
  (:require [clojure.string :as str]))

(defn- removed-a [word]
  (str/replace word "a" ""))

(defn- bouns-score-c-in-word [word]
  (if (str/includes? word "c") 5 0))

(defn- penalty-score-in-word [word]
  (if (str/includes? word "s") -7 0))

(defn- calc-word-score [word]
  (let [removed-a-word (removed-a word)
        bouns-score-c-in-word (bouns-score-c-in-word removed-a-word)
        penalty-score-in-word (penalty-score-in-word removed-a-word)
        word-score (count removed-a-word)]
    (+ word-score bouns-score-c-in-word penalty-score-in-word)))

(defn ranked-words [words]
  (sort-by calc-word-score #(compare %2 %1) words))

(defn more-than-n [numbers n]
  (filter #(> % n) numbers))

(defn divide-by-n [numbers n]
  (filter #(= 0 (mod % n)) numbers))

(defn less-than-n [numbers n]
  (filter #(< % n) numbers))

(defn include-s-more-than-n [words n]
  (filter #(<= n (count (filter #{\s} %))) words))

(defn sum-of-numbers [numbers]
  (reduce + numbers))

(defn sum-of-word-lengths [words]
  (let [word-length (map count words)]
    (reduce + word-length)))

(defn count-include-s [words]
  (let [include-s-word (filter #(str/includes? % "s") words)]
    (count include-s-word)))

(defn max-of-numbers [numbers]
  (reduce max numbers))

(comment
  (ranked-words ["java" "scala" "clojure" "kotlin"])

  (map #(calc-word-score %) ["java" "scala" "clojure" "kotlin"])

  (def words ["java" "scala" "clojure" "kotlin"])

  (sort-by calc-word-score #(compare %2 %1) words)
  (reverse (sort-by calc-word-score words))
  (sort-by #(calc-word-score %) #(compare %2 %1) words)
  (sort-by calc-word-score #(compare %2 %1) words)
  (sort #(> (calc-word-score %1) (calc-word-score %2)) words)
  (sort #(apply > (map calc-word-score %)) words)
  (sort #(apply > (map calc-word-score %&)) words)
  (sort #(> (calc-word-score %1) (calc-word-score %2)) words)

  (sort-by calc-word-score #(compare %2 %1) words)
  (sort-by calc-word-score #(- (compare %1 %2)) words)  ; compare + - で降順
  (sort-by calc-word-score #(> %1 %2) words)
  (sort-by calc-word-score > words)

  (sort #(compare %2 %1) [:a :b :c])
  (sort-by identity #(compare %2 %1) [:a :b :c])

  (compare "abc" "eef")
  (int \a)
  
  (defn count-include-s2 [words]
    (let [s-count (map #(if (str/includes? % "s") 1 0) words)]
      (reduce + s-count)))
  (count-include-s2 ["clojure" "scala" "java" "kotlin"]))
