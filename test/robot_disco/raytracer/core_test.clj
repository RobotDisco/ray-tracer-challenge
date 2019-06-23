(ns robot-disco.raytracer.core-test
  (:require [clojure.test :refer :all]
            [robot-disco.raytracer.core :as ray]))

(deftest is-point
  (is (ray/point? {:x 4.3 :y -4.2 :z 3.1 :w 1.0})))

(deftest is-not-point
  (is (not (ray/point? {:x 4.3 :y -4.2 :z 3.1 :w 0.0}))))

(deftest is-vector
  (is (ray/vector? {:x 4.3 :y -4.2 :z 3.1 :w 0.0})))

(deftest is-not-vector
  (is (not (ray/vector? {:x 4.3 :y -4.2 :z 3.1 :w 1.0}))))


