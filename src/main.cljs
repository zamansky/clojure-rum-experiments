(ns main
  (:require [cljs.core.async :refer (chan put! <! go go-loop timeout)]
            [rum.core :as rum]
            [dommy.core :refer-macros [sel sel1]]
            ))



(defn login[state]
  (print state)
  )

(defn logout[state]
  (print state)
  )

(defn login-enter[e state]
  (if (= "Enter" (.-key e))
    (login state)
    )
  )


(rum/defc input [type name state field]
  [:div
   [:label.text-tray-500.font-bold.md:text-right.mx-4.py-1 (str name ": ")]
   [:input.appearance-none.h-1.bg-gray-200.border-2.border-gray200.rounded.px-2.py-4
    {:type type
     :value (field @state)
     :on-change #(swap!  state assoc field  (-> % .-target .-value))
     on-key-up #(login-enter % state)
     }
    ]
   ]
  )

(rum/defcs login-form < (rum/local {:username "un" :password "pw"} :cred)
  [state]
  (let [creds (:cred state)]
    [:div.flex
     (input "text" "username" creds :username)
     (input "text" "password" creds :password)
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
