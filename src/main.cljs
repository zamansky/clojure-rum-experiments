(ns main
  (:require [cljs.core.async :refer (chan put! <! go go-loop timeout)]
            [rum.core :as rum]
            [dommy.core :refer-macros [sel sel1]]
            ))

(rum/defcs stateful < (rum/local {:username "un" :password "pw"} :cred)
  [state]
  (let [creds (:cred state)]
    [:div  "applesauce: " (:password @creds)]))


(rum/defc main-component []
  [:div
   [:div.p-5 "Hello World"]
   (stateful )

   ]
  )
(defn reload! []
  (rum/mount (main-component) (sel1 :#app))
  (print "Hello reload!"))

(defn main! []
  (rum/mount (main-component) (sel1 :#app))
  (print "Hello main!")
  )
