#!/usr/bin/env -S clojure -M
(ns gan
  (:require [clojure.string :as str]
            [camel-snake-kebab.core :as csk]
            [jsonista.core :as json]))

(defn- read-file [path]
  (str/split-lines (slurp path)))

(defn- parse-line [line]
  (str/split line #"\t"))

(defn- keywordize-line [columns record]
  (zipmap columns record))

(defn- parse-lines [lines]
  (map parse-line lines))

(defn- keywordize-lines [lines]
  (let [columns (map #(csk/->kebab-case-keyword %) (first lines))
        records (rest lines)]
    (map (fn [record]
           (keywordize-line columns record)) records)))

(defn- keywordize-details [])

(defn- extract-details [line]
  (let [parsed-profit-details (json/read-value (:profit-details line))
        parsed-revenue-details (json/read-value (:revenue-details line))]
    (map (fn [profit-detail revenue-detail]
           (assoc {}
                  :profit {:headline (:profit-headline line)
                           :value (get profit-detail "profit")
                           :factor (get profit-detail "factor")}
                  :revenue {:headline (:revenue-headline line)
                            :value (get revenue-detail "revenue")
                            :factor (get revenue-detail "factor")})) parsed-profit-details parsed-revenue-details)))

(defn- combine-details [details-seq k]
  (let [headline (get-in (first details-seq) [k :headline])
        details (str/join "\n" (map (fn [details]
                                      (let [value (get-in details [k :value])
                                            factor (get-in details [k :factor])]
                                        (str value " " factor))) details-seq))]
    (str headline "\n\n" details)))

#_(spit "output.tsv"
      (let [data (->> (read-file "target.tsv")
                      parse-lines
                      keywordize-line
                      (map (fn [line]
                             (let [details (extract-details line)]
                               {:global-id (:global-id line)
                                :segment-id (:segment-id line)
                                :year (:year line)
                                :profit (combine-details details :profit)
                                :revenue (combine-details details :revenue)}))))
            columns [:global-id :segment-id :year :profit :revenue]
            header (str/join "\t" (map name columns))
            escape-field (fn [field]
                           (if field
                             (-> field
                                 (str/replace "\\" "\\\\")
                                 (str/replace "\n" "\\n")
                                 (str/replace "\t" "\\t")
                                 (str/replace "\r" "\\r"))
                             ""))
            rows (map (fn [row]
                        (str/join "\t" (map #(escape-field (get row %)) columns))) data)]
        (str/join "\n" (cons header rows))))

(comment
  (keywordize-lines (parse-lines (read-file "target.tsv")))

  (-> "target.tsv"
      read-file
      parse-lines
      keywordize-lines)
  
  (->> "target.tsv"
       read-file
       parse-lines
       keywordize-lines
       keywordize-details :profit)
  

  (def sample {:global-id "JP3249600002",
               :segment-id "617281e1-1617-4206-ab3f-f3a388a45e9f",
               :year "2024",
               :profit-headline "-11.1億円と、前年比 ▲583.4億円の減益。",
               :profit-details
               "[{\"factor\": \"産業・車載用部品の減益。\", \"profit\": \"▲14.3億円\", \"citedPages\": [{\"type\": \"singlePage\", \"pageNumber\": 7}]}, {\"factor\": \"半導体関連部品における、有機材料事業における減収及び有形固定資産の減損損失等により。\", \"profit\": \"▲582.0億円\", \"citedPages\": [{\"type\": \"singlePage\", \"pageNumber\": 7}, {\"type\": \"singlePage\", \"pageNumber\": 9}]}, {\"factor\": \"その他の増益。\", \"profit\": \"＋12.9億円\", \"citedPages\": [{\"type\": \"singlePage\", \"pageNumber\": 7}]}]",
               :revenue-headline "5,671.2億円と、前年比 ▲20.3億円, -0.4%の減収。",
               :revenue-details
               "[{\"factor\": \"産業・車載用部品の増収。\", \"revenue\": \"＋84.8億円\", \"citedPages\": [{\"type\": \"singlePage\", \"pageNumber\": 6}]}, {\"factor\": \"半導体関連部品における、汎用データセンター向けFCBGAの販売減少により。\", \"revenue\": \"▲138.8億円\", \"citedPages\": [{\"type\": \"singlePage\", \"pageNumber\": 6}, {\"type\": \"singlePage\", \"pageNumber\": 9}]}, {\"factor\": \"その他の増収。\", \"revenue\": \"＋33.8億円\", \"citedPages\": [{\"type\": \"singlePage\", \"pageNumber\": 6}]}]"})

  (extract-details sample)
  (combine-details (extract-details sample) :profit) 
  (json/read-value json-str {:key-fn keyword}) 
 )
  
