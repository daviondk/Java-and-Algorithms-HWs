(defn proto-get [obj key]
  (cond
    (contains? obj key) (obj key)
    :else (proto-get (:prototype obj) key)))

(defn abstract-op [op l r]
  {:op op
   :l l
   :r r})

(def Add #(abstract-op + %2 %3))
(defn Constant [val] val)
(defn Variable [name] ())

(defn evaluate [expr vars])
()