(ns advent-of-code-2022.10b
  (:require [advent-of-code-2022.10a :refer [instructions
                                             register-during-cycle]]))


(defn sprite-overlaps-pixel-position?
  [register pixel-position]
  (#{(dec register) register (inc register)} pixel-position))


(->> (register-during-cycle 1 instructions)
     (partition 40)
     (map (fn [row]
            (->> (map-indexed (fn [pixel-position register]
                                (if (sprite-overlaps-pixel-position?
                                     register
                                     pixel-position)
                                  "#"
                                  "."))
                              row)
                 (apply str))))
     (take 6)
     (clojure.string/join \newline)
     println)