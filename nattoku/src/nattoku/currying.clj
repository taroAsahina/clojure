(ns nattoku.currying)

(defn add [a b]
  (+ a b))

(defn curried-add [a]
  (fn [b]
    (fn [c]
      (+ a b c))))

(defn validate-field [field-name validation-fn]
  (fn [data]
    (if (validation-fn (get data field-name))
      data
      (throw (ex-info "Validation failed" {:field field-name})))))

(def validate-email (validate-field :email #(re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" %)))
(def validate-age (validate-field :age #(> % 0)))

(comment
  (add 1 2)
  ((curried-add 1) 2)
  (((curried-add 1) 2) 3)

  (validate-email {:email "test@example.com"})
  (validate-age {:age 20})
  (validate-age {:age -1})