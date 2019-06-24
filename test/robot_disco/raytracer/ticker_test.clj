(ns robot-disco.raytracer.ticker-test
  (:require [clojure.test :refer :all]
            [robot-disco.raytracer.core :as rt :refer [make-vector make-point]]
            [robot-disco.raytracer.ticker :as t]))

(deftest test1
  (let [e (t/make-environment (make-vector 0 1 0)
                             (make-vector 0 0 0))
        p (t/make-projectile (make-point 0 1 0)
                             (make-vector 0 0 0))]
    (is (rt/= (make-point 0 1 0) (t/position (t/tick e p))))))
