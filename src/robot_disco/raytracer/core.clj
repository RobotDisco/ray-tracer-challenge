(ns robot-disco.raytracer.core
  (:require [clojure.spec.alpha :as s]))

(def ^:const EPSILON 0.00001)

(s/def ::vector (s/tuple number? number? number? (s/and
                                                  number?
                                                  zero?)))
(s/def ::point (s/tuple number? number? number? (s/and
                                                 number?
                                                 #(< (Math/abs (- 1.0 %)) EPSILON))))
(s/def ::tuple (s/or :vector ::vector :point ::point))

(defn make-tuple
  "Make a generic tuple"
  [x y z w]
  [(double x) (double y) (double z) (double w)])

(s/fdef make-tuple
  :args (s/cat :x number? :y number? :z number? :w number?)
  :ret ::tuple)

(defn make-point
  "Make a point tuple"
  [x y z]
  (make-tuple x y z 1.0))

(s/fdef make-point
  :args (s/cat :x number? :y number? :z number?)
  :ret ::point)

(defn make-vector
  "Make a vector tuple"
  [x y z]
  (make-tuple x y z 0.0))

(s/fdef make-vector
  :args (s/cat :x number? :y number? :z number?)
  :ret ::vector)

(defn x
  "tuple x coordinate"
  [t]
  (t 0))

(s/fdef x
  :args (s/cat :tuple ::tuple)
  :ret number?)

(defn y
  "tuple y coordinate"
  [t]
  (t 1))

(s/fdef y
  :args (s/cat :tuple ::tuple)
  :ret number?)

(defn z
  "tuple z coordinate"
  [t]
  (t 2))

(s/fdef z
  :args (s/cat :tuple ::tuple)
  :ret number?)

(defn w
  "tuple w/type coordinate"
  [t]
  (t 3))

(s/fdef w
  :args (s/cat :tuple ::tuple)
  :ret number?)

(def point?
  "Is this map a point?"
  (partial s/valid? ::point))

(s/fdef point?
  :args (s/cat :tuple ::tuple)
  :ret boolean?)

(def vector?
  "Is this map a vector?"
  (partial s/valid? ::vector))

(s/fdef vector?
  :args (s/cat :tuple ::tuple)
  :ret boolean?)

(defn tuplewise
  "Perform an operation on every entry of the tuple"
  [f & tuples]
  (into [] (apply map f tuples)))

(defn +
  "Add n tuples together"
  [& tuples]
  (apply tuplewise clojure.core/+ tuples))

(defn -
  "Subtract n tuples from first. In unary form, negate all tuple values."
  ([& tuples] (apply tuplewise clojure.core/- tuples)))

(defn *
  "Multiply tuple by a scalar"
  [t s]
  (tuplewise (partial clojure.core/* s) t))

(defn /
  "Divide tuple by a scalar"
  [t s]
  (tuplewise #(clojure.core// % s) t))

(defn magnitude
  "Give magnitude of vector"
  [v]
  (Math/sqrt (clojure.core/+ (Math/pow (x v) 2.0)
                             (Math/pow (y v) 2.0)
                             (Math/pow (z v) 2.0)
                             (Math/pow (w v) 2.0))))

(s/fdef magnitude
  :args (s/cat :vector ::vector)
  :ret pos?)

(defn normalize
  "Normalize vector"
  [v]
  (let [mag (magnitude v)]
    (/ v mag)))

(s/fdef normalize
  :args (s/cat :vector ::vector)
  :ret ::vector
  :fn #(not (zero? (magnitude %))))

(defn =
  "Are two tuples close enough to another (every equivalent coord within epsilon)"
  [a b]
  (every? #(< % EPSILON)
          (map (comp #(Math/abs %) clojure.core/-) a b)))

(s/fdef =
  :args (s/tuple ::tuple ::tuple)
  :ret boolean?)

(defn dot
  "Compute dot product of two vectors"
  [v1 v2]
  (reduce clojure.core/+
          (map clojure.core/* v1 v2)))

(s/fdef dot
  :args (s/tuple ::vector ::vector)
  :ret number?)

(defn cross
  "Compute cross product of two vectors"
  [[ax ay az] [bx by bz]]
  (let [- clojure.core/-
        * clojure.core/*]
    (make-vector
     (- (* ay bz) (* az by))
     (- (* az bx) (* ax bz))
     (- (* ax by) (* ay bx)))))

(s/fdef cross
  :args (s/tuple ::vector ::vector)
  :ret ::vector)
