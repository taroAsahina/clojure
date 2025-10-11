(ns flights-api.gateway.flight
  (:require [flights-api.driver.avaisales-api-driver :as avaisales-api-driver]
            [flights-api.port.flight :as flight-port]
            [integrant.core :as ig]))

(defn get-special-offers'
  [deps options]
  (let [{:keys [status body]}
        (avaisales-api-driver/get-special-offers deps options)]
    (if (= status 200)
      body
      (throw (Exception. (str "Failed to get special offers: " body))))))

(defn- new-flight-gateway
  [deps]
  (reify flight-port/FlightPort
    (get-special-offers [_ options]
      (get-special-offers' deps options))))

(defmethod ig/init-key ::flight-gateway
  [_ deps]
  (new-flight-gateway deps))