(ns robot-disco.raytracer.core-test
  (:require [clojure.test :refer :all]
            [robot-disco.raytracer.core :as ray :refer [make-point make-vector]]))

(deftest new-point
  (let [result (make-point 4.3 -4.2 3.1)]
    (is (== 4.3 (ray/x result)))
    (is (== -4.2 (ray/y result)))
    (is (== 3.1 (ray/z result)))
    (is (ray/point? result))
    (is (not (ray/vector? result)))))

(deftest new-vector
  (let [result (make-vector 4.3 -4.2 3.1)]
    (is (== 4.3 (ray/x result)))
    (is (== -4.2 (ray/y result)))
    (is (== 3.1 (ray/z result)))
    (is (ray/vector? result))
    (is (not (ray/point? result)))))

(deftest add-tuple
  (let [result (ray/+ (make-point 3 -2 5)
                      (make-vector -2 3 1))]
  (is (= (make-point 1 1 6) result))
  (is (ray/point? result))))
