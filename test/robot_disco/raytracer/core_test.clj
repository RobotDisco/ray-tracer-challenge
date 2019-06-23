(ns robot-disco.raytracer.core-test
  (:require [clojure.test :refer :all]
            [robot-disco.raytracer.core :as ray :refer [make-point make-vector make-tuple]]))

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
  (let [result1 (ray/+ (make-point 3 -2 5)
                       (make-vector -2 3 1))
        result2 (ray/+ (make-vector 3 -2 5)
                       (make-vector -2 3 1))
        result3 (ray/+ (make-point 3 -2 5)
                       (make-point -2 3 1))]
  (is (= (make-point 1 1 6) result1))
  (is (ray/vector? result2))
  (is (not (ray/vector? result3)))
  (is (not (ray/point? result3)))))

(deftest subtract-tuple
  (let [result1 (ray/- (make-point 3 2 1)
                       (make-point 5 6 7))
        result2 (ray/- (make-point 3 2 1)
                       (make-vector 5 6 7))
        result3 (ray/- (make-vector 3 2 1)
                       (make-vector 5 6 7))
        result4 (ray/- (make-vector 3 2 1)
                       (make-point 5 6 7))]
    (is (= (make-vector -2 -4 -6) result1))
    (is (ray/point? result2))
    (is (ray/vector? result3))
    (is (not (ray/vector? result4)))
    (is (not (ray/point? result4)))))

(deftest negate-tuple
  (let [result (ray/- (make-tuple 1 -2 3 -4))]
    (is (= (make-tuple -1 2 -3 4) result))))

(deftest multiply-tuple
  (let [result1 (ray/* (make-tuple 1 -2 3 -4) 3.5)
        result2 (ray/* (make-tuple 1 -2 3 -4) 0.5)]
    (is (= (make-tuple 3.5 -7 10.5 -14) result1))
    (is (= (make-tuple 0.5 -1 1.5 -2) result2))))

(deftest divide-tuple
  (let [result1 (ray// (make-tuple 1 -2 3 -4) 2)]
    (is (= (make-tuple 0.5 -1 1.5 -2) result1))))

(deftest normal-vector-magnitudes
  (is (= 1.0 (ray/magnitude (make-vector 1 0 0))))
  (is (= 1.0 (ray/magnitude (make-vector 0 1 0))))
  (is (= 1.0 (ray/magnitude (make-vector 0 0 1))))
  (is (= (Math/sqrt 14) (ray/magnitude (make-vector 1 2 3))))
  (is (= (Math/sqrt 14) (ray/magnitude (make-vector -1 -2 -3)))))
