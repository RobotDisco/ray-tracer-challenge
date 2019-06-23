(ns robot-disco.raytracer.core)

(def ^:const EPSILON 0.00001)

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

(defn normalize
  "Normalize vector"
  [v]
  (let [mag (magnitude v)]
    (/ v mag)))

(defn =
  "Are two tuples close enough to another (every equivalent coord within epsilon)"
  [a b]
  (every? #(< % EPSILON)
          (map (comp #(Math/abs %) clojure.core/-) a b)))

(defn dot
  "Compute dot product of vectors"
  [& vectors]
  (reduce clojure.core/+
          (apply map clojure.core/* vectors)))
