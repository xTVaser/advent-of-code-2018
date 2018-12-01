(ns day-1.core
  (:gen-class))

(defn read-input [file]
  (for [line (clojure.string/split-lines (slurp file))]
    (->> (Integer/parseInt line))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
