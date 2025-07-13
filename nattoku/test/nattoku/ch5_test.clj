(ns nattoku.ch5-test
  (:require [clojure.test :as t]
            [nattoku.ch5 :as sut]))

(t/deftest recommendated-books-test
  (t/testing "recommendated-books"
    (t/are [expected friend-name]
           (= expected (sut/recommendated-books friend-name))
      {:book "FP in Scala" :author ["Chiusano" "Bjarnason"]} "Alice"
      {:book "Get Programming with Scala" :author ["Sfregola"]} "Bob"
      {:book "Harry Potter" :author ["Rowling"]} "Charlie"
      {:book "The Lord of the Rings" :author ["Tolkien"]} "David")))


(t/deftest recommendations-test
  (t/testing "recommendations"
    (t/is (= (sut/recommendations ["Alice" "Bob" "Charlie" "David"])
             [{:book "FP in Scala" :author ["Chiusano" "Bjarnason"]}
              {:book "Get Programming with Scala" :author ["Sfregola"]}
              {:book "Harry Potter" :author ["Rowling"]}
              {:book "The Lord of the Rings" :author ["Tolkien"]}]))))

(t/deftest recommendations-by-author-test
  (t/testing "recommendations-by-author"
    (t/is (= (sut/recommendations-by-author ["Alice" "Bob" "Charlie" "David"])
             ["Chiusano" "Bjarnason" "Sfregola" "Rowling" "Tolkien"]))))

(t/deftest point-test
  (t/testing "point"
    (t/is (= (sut/point [1] [-2 7])
             [[1 -2] [1 7]]))))

(t/deftest point-3d-test
  (t/testing "point-3d"
    (t/is (= (sut/point-3d [1] [-2 7] [3 4])
             [[1 -2 3] [1 -2 4] [1 7 3] [1 7 4]]))))