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

(defmain mung
  [in]
  (let [json (ch/parse-string (slurp in) true)]
    (println (munge-cmds (:commands json) (:coords json)))))

(defmain demung
  [in]
  (let [munged-cmd (slurp in)]
    (println (ch/generate-string (demunge-cmd munged-cmd)))))
