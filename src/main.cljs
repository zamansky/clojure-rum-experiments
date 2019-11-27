(ns main
  (:require [reagent.core :as r]
            [cljs.core.async :refer (chan put! <! go go-loop timeout)]
            [rum.core :as rum]
            ))


(defn reload! []
  (print "Hello reload!"))

(defn main! []
(print "Hello main!")
)
