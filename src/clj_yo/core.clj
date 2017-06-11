(ns clj-yo.core
  (:require [clj-http.client :as client]))

(def ^:private ^:dynamic token (atom nil))

(def ^:private ^:dynamic base "https://api.justyo.co")

(defn set-token [t]
  (compare-and-set! token @token t)
  nil)

(defn- http-post [route form]
  (let [url (str base route)
        args {:form-params (assoc form "api_token" @token)
              :decode-body-headers true
              :accept :json
              :coerce :always
              :as :json}]
    (client/post url args)))

(defn- http-get [route form]
  (let [url (str base route)
        args {:query-params (assoc form "api_token" @token)
              :decode-body-headers true
              :accept :json
              :coerce :always
              :as :json}]
    (client/get url args)))

(defn whoami
  []
  (http-get "/me/" {}))

(defn inbox
  []
  (http-get "/yos" {}))

(defn yo-all
  ([]
   (http-post "/yoall/" {}))
  ([link]
   (http-post "/yoall/" {"link" link})))

(defn yo-user
  ([username]
   (http-post "/yo/" {"username" username}))
  ([username link]
   (http-post "/yo/" {"username" username "link" link}))
  ([username lattitude longitude]
    (http-post "/yo/" {"username" username "location" (str lattitude "," longitude)})))

(defn subscribers-count
  []
  (client/get (str base "/subscribers_count/") {:query-params {"api_token" @token}}))
