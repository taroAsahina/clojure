(ns flights-api.handler.v1.special-offer
  (:require [integrant.core :as ig]
            [flights-api.use-case.special-offer :as special-offer-use-case]))


;; 複数のqueryParamを想定しているので、idだけじゃないはず
(defn get-special-offers
  [deps {{:keys [origin locale currency]} :route-params}]
  (special-offer-use-case/execute deps origin locale currency))
   
 
(defmethod ig/init-key ::get-special-offers
  [_ deps]
  (partial deps get-special-offers))
  