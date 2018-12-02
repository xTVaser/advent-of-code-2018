(ns day-2.core
  (:gen-class))

(defn read-input [file]
  (for [line (clojure.string/split-lines (slurp file))]
    line))

(defn not-empty? [coll]
  (not (empty? coll)))

(defn tally-occurences [init-list]
  (loop [ids init-list
         times-two 0
         times-three 0]
    (if ids
      (let [id (first ids)
            two-occurences (filter #(= % 2) (vals (frequencies id)))
            three-occurences (filter #(= % 3) (vals (frequencies id)))]
        (cond
          (and (not-empty? two-occurences) (not-empty? three-occurences)) (recur (next ids) (inc times-two) (inc times-three))
          (not-empty? two-occurences) (recur (next ids) (inc times-two) times-three)
          (not-empty? three-occurences) (recur (next ids) times-two (inc times-three))
          :else (recur (next ids) times-two times-three)))
      [times-two times-three])))

(defn part-one
  [list]
  (let [result (tally-occurences list)]
    (* (first result) (second result))))

(defn find-differences-in-place
  [s1 s2]
  (loop [str1 s1
         str2 s2
         diff []]
    ; We know everything is the same length
    (if str1
      (let [c1 (first str1)
            c2 (first str2)]
        (if (not (= c1 c2))
          (recur (next str1) (next str2) (conj diff c1))
          (recur (next str1) (next str2) diff)))
      diff)))

(defn find-off-by-one
  [list]
  (for [i list
        j list]
    (let [diff (find-differences-in-place i j)]
      (if (= 1 (count diff))
         [i diff]))))

(defn part-two
  [list]
  (filter some? (find-off-by-one list)))

; (read-input "resources/input")
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (part-one (read-input "resources/input"))
  (part-two (read-input "resources/input")))