(ns flights-api.gateway.special-offer-test
  (:require [clojure.test :as t]
            [flights-api.gateway.special-offer :as sut]
            [flights-api.driver.special-offer :as special-offer-driver]
            [mockfn.macros :as mockfn]
            [mockfn.matchers :as mockfn-matchers]))

(t/deftest get-special-offers-test
  (t/testing "get-special-offers"
    (let [options {:origin "TYO" :locale "ja" :currency "JPY"}]
      (mockfn/verifying
       [(special-offer-driver/get-special-offers options)
        {};;next TODO
        (mockfn-matchers/exactly 1)]
       (t/is (= {:flights [{:airline "ANA"
                            :origin {:iata-code "TYO"
                                     :name "Tokyo"
                                     :airport "HND"}
                            :destination {:iata-code "THA"
                                          :name "Bangkok"
                                          :airport "BKK"}
                            :price 10000
                            :currency "JPY"
                            :departure-at "2025-09-04T10:45:00+09:00"
                            :duration 10000}]} 
                (sut/get-special-offers options)))))))

(comment
  )