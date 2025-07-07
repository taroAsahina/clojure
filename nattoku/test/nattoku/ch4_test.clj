(ns nattoku.ch4-test
  (:require
   [clojure.test :as t]
   [nattoku.ch4 :as sut]))

(t/deftest ranked-words-test
  (t/testing "ワードをランク付けして並び替える"
    (t/is (= (sut/ranked-words ["java" "scala" "clojure" "kotlin"]) ["clojure" "kotlin" "java" "scala"])))) 

(t/deftest more-than-n
  (t/testing "nより大きい数値が全て含まれたリストを返す"
    (t/is (= (sut/more-than-n [1 2 3 4 5] 1) [2 3 4 5]))))

(t/deftest divide-by-n
  (t/testing "nで割り切れる数値だけが含まれたリストを返す"
    (t/is (= (sut/divide-by-n [1 2 3 4 5] 2) [2 4]))))

(t/deftest less-than
  (t/testing "nより小さい数値が全て含まれたリストを返す"
    (t/is (= (sut/less-than-n [1 2 3 4 5] 3) [1 2]))))

(t/deftest include-s-n-words
  (t/testing "sがn個以上含まれた単語のリストを返す"
    (t/is (= (sut/include-s-more-than-n ["java" "scala" "clojure" "kotlin"] 1) ["scala"]))))

(t/deftest sum-of-numbers
  (t/testing "数値のリストの合計を返す"
    (t/is (= (sut/sum-of-numbers [1 2 3 4 5]) 15))))

(t/deftest sum-of-word-lengths
  (t/testing "単語の長さの合計を返す"
    (t/is (= (sut/sum-of-word-lengths ["clojure" "scala" "java" "kotlin"]) 22))))

(t/deftest count-include-s
  (t/testing "sが含まれた単語の数を返す"
    (t/is (= (sut/count-include-s ["clojure" "scala" "java" "kotlin"]) 1))))

(t/deftest max-of-numbers
  (t/testing "数値のリストの最大値を返す"
    (t/is (= (sut/max-of-numbers [1 2 3 4 5]) 5))))