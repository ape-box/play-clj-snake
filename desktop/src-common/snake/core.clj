(ns snake.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]))

(def entities-size 10)

(defn mk-entity [x y]
  (assoc (shape :filled
                :set-color (color :lime)
                :rect 0 0 entities-size entities-size)
    :x x
    :y y
    :speed 1
    :width entities-size
    :direction :right
    :turn :right))

(defn update-position [ent]
  (cond
    (= :right (:direction ent)) (assoc ent :x (+ (:x ent) (:speed ent)))
    (= :left (:direction ent)) (assoc ent :x (- (:x ent) (:speed ent)))
    (= :up (:direction ent)) (assoc ent :y (+ (:y ent) (:speed ent)))
    (= :down (:direction ent)) (assoc ent :y (- (:y ent) (:speed ent)))))

(defn update-direction [ent]
  (cond
    (key-pressed? :w) (assoc ent :turn :up :direction :up)
    (key-pressed? :a) (assoc ent :turn :left :direction :left)
    (key-pressed? :s) (assoc ent :turn :down :direction :down)
    (key-pressed? :d) (assoc ent :turn :right :direction :right)
    :else ent
    ))


(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage))
    [(mk-entity 0 400)
     ;;(label "Hello world!" (color :white))
     ])

  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen (map (fn [ent] (-> ent
                                       update-direction
                                       update-position)) entities))))

(defgame snake-game
  :on-create
  (fn [this]
    (set-screen! this main-screen)))
