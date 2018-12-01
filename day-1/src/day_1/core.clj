(ns day-1.core
  (:gen-class))

(defn read-input [file]
  (for [line (clojure.string/split-lines (slurp file))]
    (->> (Integer/parseInt line))))

(defn part-one
  []
  (apply + (read-input "resources/input")))

(defn part-two
  [initial-frequency]
  (loop [change-set (read-input "resources/input")
         frequencies [initial-frequency]]
    (if change-set
      (let [x (first change-set)]
        (recur (next change-set) (conj frequencies (+ x (last frequencies)))))
      (rest frequencies))))

; Taken from SO - https://stackoverflow.com/questions/19894216/clojure-find-repetition
(defn get-cycle [xs]
  (first (filter #(number? (first %))
                 (reductions
                  (fn [[m i] x] (if-let [xat (m x)] [xat i] [(assoc m x i) (inc i)]))
                  [(hash-map) 0] xs))))

(defn find-duplicate
  []
  (loop [acc '()]
    (println (count acc))
    (if (nil? (get-cycle acc))
      (let [initial (if (empty? acc) 0 (last acc))]
        (recur (concat acc (part-two initial))))
      (nth acc (first (get-cycle acc))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (part-one)
  (find-duplicate))
