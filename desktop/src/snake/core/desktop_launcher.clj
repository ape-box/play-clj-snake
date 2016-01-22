(ns snake.core.desktop-launcher
  (:require [snake.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. snake-game "snake" 800 600)
  (Keyboard/enableRepeatEvents true))
