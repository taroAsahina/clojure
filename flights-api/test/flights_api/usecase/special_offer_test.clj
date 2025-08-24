(ns flights-api.usecase.special-offer-test
  (:require
   [clojure.test :as t]
   [mockfn.macros :as mockfn]
   [mockfn.matchers :as mockfn-matchers]
   [flights-api.use-case.special-offer :as sut]
   [flights-api.gateway.special-offer :as special-offer-gateway]))

(t/deftest execute-test
  (t/testing "execute"
    (let [options {:origin "TYO" :locale "ja" :currency "JPY"}
          expected {:flights [{:airline "ANA"
                                       :origin {:iata-code "TYO"
                                                :name "Tokyo"
                                                :airport "HND"}
                                       :destination {:iata-code "THA"
                                                     :name "Bangkok"
                                                     :airport "BKK"}
                                       :price 10000
                                       :currency "JPY"
                                       :departure-at "2025-09-04T10:45:00+09:00"
                                       :duration 10000}]}]
      (mockfn/verifying
       [(special-offer-gateway/get-special-offers options)
        expected
        (mockfn-matchers/exactly 1)]
       (t/is (= expected
                (sut/execute options)))))))

(t/deftest execute-test-with-different-options
  (t/testing "execute with different options"
    (let [options {:origin "NRT" :locale "en" :currency "USD"}
          expected {:flights [{:airline "JAL"
                            :origin {:iata-code "NRT"
                                     :name "Narita"
                                     :airport "NRT"}
                            :destination {:iata-code "USA"
                                          :name "New York"
                                          :airport "JFK"}
                            :price 150000
                            :currency "USD"
                            :departure-at "2025-09-05T14:30:00+09:00"
                            :duration 72000}]}]
      (mockfn/verifying
       [(special-offer-gateway/get-special-offers options)
        expected
        (mockfn-matchers/exactly 1)]
       (t/is (= expected
                (sut/execute options)))))))
