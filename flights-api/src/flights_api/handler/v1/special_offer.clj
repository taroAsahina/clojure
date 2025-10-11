(ns flights-api.handler.v1.special-offer
  (:require [integrant.core :as ig]
            [ring.util.http-response :as response]
            [flights-api.use-case.get-special-offer :as special-offer-use-case]))

(defn get-special-offers
  [deps {{:keys [origin locale currency]} :params}]
  (try
    (let [options (cond-> {}
                    origin (assoc :origin origin)
                    locale (assoc :locale locale)
                    currency (assoc :currency currency))
          result (special-offer-use-case/execute deps options)]
      (response/ok result))
    (catch Exception e
      (response/internal-server-error
       {:error "Failed to process special offers request"
        :message (.getMessage e)}))))

(defmethod ig/init-key ::get-special-offers
  [_ deps]
  (partial get-special-offers deps))
