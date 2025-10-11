(ns flights-api.port.flight)

(defprotocol FlightPort
  (get-special-offers [this options]))