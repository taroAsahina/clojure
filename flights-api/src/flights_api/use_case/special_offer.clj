(ns flights-api.use-case.special-offer
  (:require [flights-api.gateway.special-offer :as special-offer-gateway]))

(defn execute
  [options]
  (special-offer-gateway/get-special-offers options))
