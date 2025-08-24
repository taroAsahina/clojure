(ns flights-api.handler.v1.special-offer
  (:require [integrant.core :as ig]
            [ring.util.http-response :as response]
            [flights-api.use-case.special-offer :as special-offer-use-case]))

(defn get-special-offers
  [_ {{:keys [origin locale currency]} :params}]
  (try
    (let [options (cond-> {}
                    origin (assoc :origin origin)
                    locale (assoc :locale locale)
                    currency (assoc :currency currency))
          result (special-offer-use-case/execute options)]
      (response/ok result))
    (catch Exception e
      (response/internal-server-error
       {:error "Failed to process special offers request"
        :message (.getMessage e)}))))

(defmethod ig/init-key ::get-special-offers
  [_ _]
  (partial get-special-offers))
