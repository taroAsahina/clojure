(ns nattoku.monad-example)

;; Scala: x.flatMap(x => y.map(y => point(x, y)))
;; をClojureに変換

;; ScalaのPoint型をClojureのデータ構造で表現
;; Scala: case class Point(x: Int, y: Int)
;; Clojure: マップまたはベクターで表現

;; 方法1: マップとして表現
(defn point [x y]
  {:x x :y y})

;; 方法2: ベクターとして表現（位置情報の場合）
(defn point-vec [x y]
  [x y])

;; 例1: リストを使用した場合（マップ版）
(defn flatmap-example []
  (let [x [1 2 3]
        y [10 20]]
    (for [x-val x
          y-val y]
      (point x-val y-val))))

;; 例1b: ベクター版
(defn flatmap-example-vec []
  (let [x [1 2 3]
        y [10 20]]
    (for [x-val x
          y-val y]
      (point-vec x-val y-val))))

;; 例2: より明示的なflatMap/mapの実装
(defn flatmap [coll f]
  (apply concat (map f coll)))

(defn flatmap-example2 []
  (let [x [1 2 3]
        y [10 20]]
    (flatmap x
             (fn [x-val]
               (map (fn [y-val]
                      (point x-val y-val))
                    y)))))

;; 例3: 実際の使用例
(comment
  ;; 実行例
  (flatmap-example)
  ;; => ({:x 1 :y 10} {:x 1 :y 20} {:x 2 :y 10} {:x 2 :y 20} {:x 3 :y 10} {:x 3 :y 20})
  
  (flatmap-example-vec)
  ;; => ([1 10] [1 20] [2 10] [2 20] [3 10] [3 20])
  
  (flatmap-example2)
  ;; => ({:x 1 :y 10} {:x 1 :y 20} {:x 2 :y 10} {:x 2 :y 20} {:x 3 :y 10} {:x 3 :y 20})
  
  ;; より複雑な例
  (let [x [1 2 3]
        y [10 20 30]]
    (for [x-val x
          y-val y
          :when (even? (+ x-val y-val))]
      (point x-val y-val)))
  ;; => ({:x 1 :y 11} {:x 2 :y 10} {:x 2 :y 12} {:x 3 :y 11})
  
  ;; ベクター版の複雑な例
  (let [x [1 2 3]
        y [10 20 30]]
    (for [x-val x
          y-val y
          :when (even? (+ x-val y-val))]
      (point-vec x-val y-val)))
  ;; => ([1 11] [2 10] [2 12] [3 11])
  )