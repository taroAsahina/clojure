(ns flights-api.driver.avaisales-api-driver
  (:require [clj-http.client :as http]))

(defn get-special-offers
  [{:keys [avaisales-api-url]} options]
  (http/get (str avaisales-api-url "/aviasales/v3/get_special_offers")
            {:query-params (assoc options :token "67d6e4c5f5a27cff7226a40ec93ca468")
             :accept :json
             :as :json}))