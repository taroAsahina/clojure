(ns flights-api.use-case.get-special-offer
  (:require [flights-api.port.flight :as flight-port]))

(defn execute
  [{:keys [flight-gateway]} options]
  (flight-port/get-special-offers flight-gateway options))
