(ns robot-disco.raytracer.ticker
  (:require [robot-disco.raytracer.core :as rt]))

(defn make-projectile
  [pos vel]
  {:position pos
   :velocity vel})

(def position :position)
(def velocity :velocity)

(defn make-environment
  [gravity wind]
  {:gravity gravity
   :wind wind})

(def gravity :gravity)
(def wind :wind)

(defn tick
  [env proj]
  {:position (rt/+ (position proj) (velocity proj))
   :velocity (rt/+ (velocity proj) (gravity env) (wind env))})
