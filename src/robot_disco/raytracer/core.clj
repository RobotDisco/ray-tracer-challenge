(ns robot-disco.raytracer.core)

(defn point?
  "Is this map a point?"
  [p]
  (let [w (get p :w)]
    (and w
         (== w 1.0))))

(defn vector?
  "Is this map a vector?"
  [v]
  (let [w (get v :w)]
    (and w
         (== w 0.0))))
