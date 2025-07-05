(ns nattoku.ch3-test
  (:require
   [clojure.test :as t]
   [nattoku.ch3 :as ch3]))

(t/deftest test-abbreviate
  (t/testing "abbreviate"
    (t/is (= (ch3/abbreviate "taro asahina") "T. asahina"))
    (t/is (= (ch3/abbreviate "Asahina taro") "A. taro"))
    (t/is (= (ch3/abbreviate "T asahina") "T. asahina"))))

(t/deftest test-first-two
  (t/testing "first-to"
    (t/is (= (ch3/first-two ["Apple" "Book" "Mango"]) ["Apple" "Book"]))))

(t/deftest test-last-two
  (t/testing "last-two"
    (t/is (= (ch3/last-two ["Apple" "Book" "Mango"]) ["Book" "Mango"]))))

(t/deftest test-moved-first-two-to-the-end
  (t/testing "moved-first-two-to-the-end"
    (t/is (= (ch3/moved-first-two-to-the-end ["Apple" "Book" "Mango"]) ["Mango" "Apple" "Book" ]))))
