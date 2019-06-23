(ns robot-disco.raytracer.core)

(defn make-tuple
  "Make a generic tuple"
  [x y z w]
  [(double x) (double y) (double z) (double w)])

(defn make-point
  "Make a point tuple"
  [x y z]
  (make-tuple x y z 1.0))

(defn make-vector
  "Make a vector tuple"
  [x y z]
  (make-tuple x y z 0.0))

(defn x
  "tuple x coordinate"
  [t]
  (t 0))

(defn y
  "tuple y coordinate"
  [t]
  (t 1))

(defn z
  "tuple z coordinate"
  [t]
  (t 2))

(defn w
  "tuple w/type coordinate"
  [t]
  (t 3))

(defn point?
  "Is this map a point?"
  [t]
  (let [w (w t)]
    (and w
         (== w 1.0))))

(defn vector?
  "Is this map a vector?"
  [t]
  (let [w (w t)]
    (and w
         (== w 0.0))))

(defn +
  "Add two tuples together"
  [a b]
  (into [] (map clojure.core/+ a b)))

(defn -
  "Subtract one tuple from another. In unary form, subtract tuple from (0,0,0) vector."
  ([a]  (into [] (map clojure.core/- a)))
  ([a b] (into [] (map clojure.core/- a b))))

(defn *
  "Multiply tuple by a scalar"
  [t s]
  (into [] (map #(clojure.core/* % s) t)))

(defn /
  "Divide tuple by a scalar"
  [t s]
  (into [] (map #(clojure.core// % s) t)))

(defn magnitude
  "Give magnitude of vector"
  [v]
  (Math/sqrt (clojure.core/+ (Math/pow (x v) 2.0)
                             (Math/pow (y v) 2.0)
                             (Math/pow (z v) 2.0)
                             (Math/pow (w v) 2.0))))
