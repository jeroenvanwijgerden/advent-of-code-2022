(ns advent-of-code-2022.10a)


(def instructions (->> (slurp "resources/1.txt")
                       (clojure.string/split-lines)))


(defn register-transformation-after-cycle
  "Returns a seq of fns that take a single argument: the cpu register."
  [instructions]
  (mapcat (fn [instruction]
            (let [parts (clojure.string/split instruction #" ")]
              (case (first parts)
                "noop" [identity]
                "addx" (let [n (Integer/parseInt (second parts))]
                         [identity
                          (fn [register] (+ register n))]))))
          instructions))


(defn register-during-cycle
  [initial instructions]
  (reductions (fn [register transformation]
                (transformation register))
              initial
              (register-transformation-after-cycle instructions)))


(defn signal-strength-during-cycle
  [register-during-cycle]
  (map-indexed (fn [idx register]
                 (* (inc idx) register))
               register-during-cycle))


(->> (register-during-cycle 1 instructions)
     signal-strength-during-cycle
     (drop 19)
     (take-nth 40)
     (take 6)
     (reduce +))