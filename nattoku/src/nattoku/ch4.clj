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

(comment
  (calc-word-score "java")
  (calc-word-score "scala")
  (calc-word-score "clojure")
  (calc-word-score "kotlin")
  (ranked-words ["java" "scala" "clojure" "kotlin"])
  
  (map #(calc-word-score %) ["java" "scala" "clojure" "kotlin"]) 

  (def words ["java" "scala" "clojure" "kotlin"])

  (sort-by calc-word-score #(compare %2 %1) words)
  (reverse (sort-by calc-word-score words))
  (sort-by #(calc-word-score %) #(compare %2 %1) words)
  (sort-by calc-word-score #(compare %2 %1) words))