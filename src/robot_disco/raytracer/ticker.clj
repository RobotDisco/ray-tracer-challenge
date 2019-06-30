(ns robot-disco.raytracer.ticker
  (:require [robot-disco.raytracer.core :as rt]
            [clojure.spec.alpha :as s]))

(s/def ::position :robot-disco.raytracer.core/point)
(s/def ::velocity :robot-disco.raytracer.core/vector)
(s/def ::projectile (s/keys :req-un [::position ::velocity]))

(s/def ::gravity :robot-disco.raytracer.core/vector)
(s/def ::wind :robot-disco.raytracer.core/vector)
(s/def ::environment (s/keys :req-un [::gravity ::wind]))

(defn make-projectile
  [pos vel]
  {:position pos
   :velocity vel})

(s/fdef make-projectile
  :args (s/tuple ::position ::velocity))

(def position :position)

(s/fdef position
  :args (s/tuple ::projectile)
  :ret ::position)

(def velocity :velocity)

(s/fdef velocity
  :args (s/tuple ::projectile)
  :ret ::velocity)

(defn make-environment
  [gravity wind]
  {:gravity gravity
   :wind wind})

(s/fdef make-environment
  :args (s/tuple ::gravity ::wind)
  :ret ::environment)

(def gravity :gravity)
(s/fdef gravity
  :args (s/tuple ::environment)
  :ret ::gravity)

(def wind :wind)
(s/fdef wind
  :args (s/tuple ::environment)
  :ret ::wind)

(defn tick
  [env proj]
  {:position (rt/+ (position proj) (velocity proj))
   :velocity (rt/+ (velocity proj) (gravity env) (wind env))})

(s/fdef tick
  :args (s/tuple ::environment ::projectile)
  :ret ::projectile)
