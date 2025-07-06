(ns nattoku.ch4-test
  (:require
   [clojure.test :as t]
   [nattoku.ch4 :as sut]))

(t/deftest ranked-words-test
  (t/testing "ワードをランク付けして並び替える"
    (t/is (= (sut/ranked-words ["java" "scala" "clojure" "kotlin"]) ["clojure" "kotlin" "java" "scala"])))) 