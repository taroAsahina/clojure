(ns nattoku.nattoku-shitai)

(def x1 (->> 1
             (+ 1)
             (-> (+ 1))))
(def x2 (-> (+ 1)
            2))
;; x2になると思っていたが、x3が正解っぽい。(macpoexpandによると)
;; つまり、threeding-macroは、評価された値が渡されるということではなく、関数が渡されている？
;; 評価されるのはmacroが最後まで行き着いたとき？
(def x3 (-> (+ 1)
         (+ 1 1)))

(def y1 (-> 1
            (+ 1)
            (->> (+ 1))))
(def y2 (->> 2
             (+ 1)))

(comment
  x1
  x2
  x3
  y1
  y2
  
  (macroexpand '(-> 1
                    (+ 1)
                    (->> (+ 1))))
  (macroexpand '(->> 1
                    (+ 1)
                    (-> (+ 1))))
  (macroexpand '(-> (+ 1)
                    (+ 1 1)))
  )