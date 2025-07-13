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

;; curryring
(defn more-than-n-curried [n]
  (fn [numbers]
    (filter #(> % n) numbers)))

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
  (count-include-s2 ["clojure" "scala" "java" "kotlin"])
  
  (more-than-n [1 2 3] 2)
  ((more-than-n-curried 2) [1 2 3])
  
  ;; ===== more-than-n-curried だけを使ったカリー化の利点 =====
  
  ;; 1. 部分適用による再利用性
  (def more-than-5 (more-than-n-curried 5))
  (def more-than-10 (more-than-n-curried 10))
  
  (more-than-5 [1 3 5 7 9 11])  ; => [7 9 11]
  (more-than-10 [1 3 5 7 9 11]) ; => [11]
  
  ;; 元の関数だと毎回引数を指定する必要がある
  (more-than-n [1 3 5 7 9 11] 5)
  (more-than-n [1 3 5 7 9 11] 10)
  
  ;; 2. 複数のデータセットに同じ条件を適用
  (def test-data [[1 2 3 4 5]
                  [6 7 8 9 10]
                  [11 12 13 14 15]])
  
  ;; 各データセットに同じフィルターを適用
  (map more-than-5 test-data)
  ;; 結果: [[6 7 8 9 10] [6 7 8 9 10] [11 12 13 14 15]]
  
  ;; 元の関数だと map で使えない
  ;; (map #(more-than-n % 5) test-data)  ; これは動くけど...
  
  ;; 3. 高階関数との組み合わせ
  (defn apply-multiple-filters [numbers filters]
    (reduce #(%2 %1) numbers filters))
  
  ;; 複数のフィルターを順次適用
  (apply-multiple-filters [1 2 3 4 5 6 7 8 9 10] 
                         [(more-than-n-curried 3)
                          (more-than-n-curried 5)])
  
  ;; 元の関数だとこうなる（可読性が悪い）
  ;; (->> [1 2 3 4 5 6 7 8 9 10]
  ;;      (more-than-n 3)
  ;;      (more-than-n 5))
  
  
  ;; 4. 関数として渡せる
  (defn apply-filter [numbers filter-fn]
    (filter-fn numbers))
  
  (apply-filter [1 2 3 4 5] more-than-5)
  (apply-filter [1 2 3 4 5] (more-than-n-curried 3))
  
  ;; 5. 条件を動的に変更
  (def thresholds [3 5 7])
  (def filters (map more-than-n-curried thresholds))
  
  (map #(% [1 2 3 4 5 6 7 8 9 10]) filters)
  ;; 結果: [[4 5 6 7 8 9 10] [6 7 8 9 10] [8 9 10]]
  )
