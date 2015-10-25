(ns multicmd.run
  (:require [clojure.string :as s]
            [cheshire.core  :as ch]
            [defmain.core   :refer [defmain]]))

(defn munge-cmds
  [cmds coords]
  (apply str
         (concat
          ["summon FallingSand " (or coords "~ ~1 ~") " {Block:activator_rail,Time:1,Passengers:[{id:MinecartCommandBlock,"]
          (take (count cmds)
                (repeat "Passengers:[{id:MinecartCommandBlock,"))
          ["Command:"]
          (interleave (reverse cmds)
                      (repeat "}],Command:"))
          ["setblock ~ ~ ~ lava 7}]}"])))

(defn demunge-cmd
  [cmd]
  {:coords
   (let [coords (second (re-find #"^\s*summon\s*FallingSand([^{]*)" cmd))]
     (when coords
       (s/trim coords)))
   :commands
   (reverse
    (butlast
     (map (comp s/trim second)
          (re-seq #",\s*Command\s*:([^}]*)" cmd))))})

(defn munge-file
  [in]
  (let [json (ch/parse-string (slurp in) true)]
    (munge-cmds (:commands json) (:coords json))))

(defn demunge-file
  [in]
  (let [munged-cmd (slurp in)]
    (ch/generate-string (demunge-cmd munged-cmd))))

(defmain mung
  [in]
  (println (munge-file in)))

(defmain demung
  [in]
  (println (demunge-file in)))
