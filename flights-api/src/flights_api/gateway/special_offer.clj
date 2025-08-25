(ns flights-api.gateway.special-offer
  (:require [flights-api.driver.special-offer :as driver]))

(defn get-special-offers
  [options]
  (driver/get-special-offers options))

