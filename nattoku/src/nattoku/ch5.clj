(ns nattoku.ch5)

(defn recommendated-books [friend-name]
  (case friend-name
    "Alice" {:book "FP in Scala" :author ["Chiusano" "Bjarnason"]}
    "Bob" {:book "Get Programming with Scala" :author ["Sfregola"]}
    "Charlie" {:book "Harry Potter" :author ["Rowling"]}
    {:book "The Lord of the Rings" :author ["Tolkien"]}))

(defn recommendations [friend-names]
  (map recommendated-books friend-names))

(defn recommendations-by-author [friend-names]
  (flatten (map :author (recommendations friend-names))))

(defn point [xs ys]
  (for [x xs
        y ys]
    [x y]))

(defn point-3d [xs ys zs]
  (for [x xs
        y ys
        z zs]
    [x y z]))

(comment
  (flatten (map :author (recommendations ["Alice" "Bob"])))
  (recommendations-by-author ["Alice" "Bob"])
  (point [1] [-2 7])
  )