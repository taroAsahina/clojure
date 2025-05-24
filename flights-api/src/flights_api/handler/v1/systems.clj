(ns flights-api.handler.v1.systems
  (:require
   [integrant.core :as ig]
   [ring.util.http-response :as response]))

(defn ping
  [_]
  (response/ok "pong"))

(defmethod ig/init-key ::ping
  [_ _]
  ping)