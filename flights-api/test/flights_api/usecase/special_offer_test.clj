(ns flights-api.usecase.special-offer-test
  (:require
   [clojure.test :as t]
   [mockfn.macros :as mockfn]
   [mockfn.matchers :as mockfn-matchers]
   [flights-api.use-case.special-offer :as sut]
   [flights-api.gateway.special-offer :as special-offer-gateway]))


;;FIXME なんか実態が呼ばれるmockがうまく行ってない
(t/deftest execute-test
  (t/testing "execute"
    (let [options {:origin "TYO" :locale "ja" :currency "JPY"}]
      (mockfn/verifying
       (special-offer-gateway/get-special-offers options)
       {:flights [{:airline "ANA"
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
       (mockfn-matchers/exactly 1)) 
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
               (sut/execute options))))))
