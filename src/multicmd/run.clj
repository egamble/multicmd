(ns multicmd.run
  (:require [clojure.string :as str]
            [defmain.core   :refer [defmain]]))

(defn munge-cmds
  [cmds]
  "foobar")

(defn demunge-cmd
  [cmd]
  ["foo" "bar"])

(defmain mung
  []
  (let [cmds (str/split-lines (slurp *in*))]
    (println (munge-cmds cmds))))

(defmain demung
  []
  (let [munged-cmd (slurp *in*)]
    (doseq [cmd (demunge-cmd munged-cmd)]
      (println cmd))))
