(defn op [operation]
  (fn [& args]
    (fn [vars]
      (apply operation (mapv #(% vars) args)))
    )
  )

(def add (op +))
(def subtract (op -))
(def negate (op -))
(def multiply (op *))
(def divide (op #(/ (double %1) (double %2))))
(def sinh (op #(Math/sinh %)))
(def cosh (op #(Math/cosh %)))
(defn constant [num] (fn [vars] num))
(defn variable [name] #(get % name))


(defn parseFunction [expr]
  (let [ops {:negate subtract :+ add :- subtract  :* multiply :/ divide :sinh sinh :cosh cosh}
        getOp #(get ops (keyword %))]
    (cond
      (string? expr) (parseFunction (read-string expr))
      (list? expr) (apply (getOp (first expr)) (mapv parseFunction (rest expr)))
      (number? expr) (constant expr)
      (symbol? expr) (variable (str expr))
      )
    )
  )
(println  ((parseFunction "(+ (/) (-) (+) (*) 1)") {}))