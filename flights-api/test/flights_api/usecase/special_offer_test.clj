(ns flights-api.usecase.special-offer-test
  (:require
   [clojure.test :as t]
   [mockfn.macros :as mockfn]
   [mockfn.matchers :as mockfn-matchers]
   [flights-api.use-case.get-special-offer :as sut]
   [flights-api.port.flight :as flight-port]))

(t/deftest execute-test
  (t/testing "execute"
    (let [flight-gateway {:flight-gateway :flight-gateway'}
          options {:origin "TYO" :locale "ja" :currency "JPY"}
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
       [(flight-port/get-special-offers :flight-gateway' options)
        expected
        (mockfn-matchers/exactly 1)]
       (t/is (= expected
                (sut/execute flight-gateway options)))))))
