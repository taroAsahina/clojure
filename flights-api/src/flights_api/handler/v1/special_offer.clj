(ns flights-api.handler.v1.special-offer
  (:require [integrant.core :as ig]
            [flights-api.use-case.special-offer :as special-offer-use-case]))

;; strsじゃなくてkeysのほうがよい？
(defn get-special-offers
  [deps {{:strs [origin locale currency] :as query-params-map} :query-params}]
  (if query-params-map
    (do
      (prn query-params-map)
      (prn "Destructured query params: origin=" origin ", locale=" locale ", currency=" currency)
      (special-offer-use-case/execute deps origin locale currency))
    (do
      (prn "Query params map is nil or not provided!")
      {:status 500 :body "Query params missing"})))
 
(defmethod ig/init-key :flights-api.handler.v1.special-offer/get-special-offers
  [_ deps]
  (partial get-special-offers deps))
