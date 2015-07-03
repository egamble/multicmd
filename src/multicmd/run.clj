(ns multicmd.run
  (:require [clojure.string :as s]
            [cheshire.core  :as ch]
            [defmain.core   :refer [defmain]]))

(defn munge-cmds
  [cmds coords]
  (apply str
         (concat
          ["summon MinecartCommandBlock " (or coords "~ ~1 ~") " {Riding:"]
          (take (inc (count cmds))
                (repeat "{id:MinecartCommandBlock,Riding:"))
          ["{id:FallingSand,Block:activator_rail,Time:1}"]
          (interleave (repeat "},Command:")
                      cmds)
          ["},Command:setblock ~ ~ ~ lava 7}"])))

(defn demunge-cmd
  [cmd]
  {:coords
   (let [coords (second (re-seq #"^\s*summon\s*Minecartcommandblock([^{]*)" cmd))]
     (when coords
       (s/trim coords)))
   :commands
   (butlast
    (map (comp s/trim second)
         (re-seq #",\s*Command\s*:([^}]*)" cmd)))})

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
