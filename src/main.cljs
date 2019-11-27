(ns main
  (:require [cljs.core.async :refer (chan put! <! go go-loop timeout)]
            [rum.core :as rum]
            [dommy.core :refer-macros [sel sel1]]
            ))



(defn login[local-state]
  (print local-state)
  )

(defn logout[local-state]
  (print local-state)
  )

(defn login-enter[e local-state]
  (if (= "Enter" (.-key e))
    (login local-state)
    )
  )


(rum/defc input [type name local-state field]
  [:div
   [:label.text-tray-500.font-bold.md:text-right.mx-4.py-1 (str name ": ")]
   [:input.appearance-none.h-1.bg-gray-200.border-2.border-gray200.rounded.px-2.py-4
    {:type type
     :value (field @local-state)
     :on-change #(swap!  local-state assoc field  (-> % .-target .-value))
     on-key-up #(login-enter % local-state)
     }]])

(rum/defcs login-form < (rum/local {:username "" :password ""} :cred)
  [local-state]
  (let [creds (:cred local-state)]
    [:div.flex
     (input "text" "username" creds :username)
     (input "password" "password" creds :password)
     [:button.bg-blue-500.hover:bg-blue-700.text-white.font-bold.px-3..mx-4.my-1.rounded {:on-click #(login creds) :id "login"}"Login"]
     ]
    
    ))



(rum/defc main-component []
[:div
[:div.p-5 "Hello World"]
(login-form)

]
)
(defn reload! []
(rum/mount (main-component) (sel1 :#app))
(print "Hello reload!"))

(defn main! []
(rum/mount (main-component) (sel1 :#app))
(print "Hello main!")
)
