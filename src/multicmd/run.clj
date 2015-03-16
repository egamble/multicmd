(ns multicmd.run
  (:require [clojure.string :as str]
            [defmain.core   :refer [defmain]]))

(defn munge-cmds
  [cmds]
  (apply str
         (concat
          ["summon MinecartCommandBlock ~ ~1 ~ {Riding:"]
          (take (inc (count cmds))
                (repeat "{id:MinecartCommandBlock,Riding:"))
          ["{id:FallingSand,TileID:157,Time:1}"]
          (interleave (repeat "},Command:")
                      cmds)
          ["},Command:setblock ~ ~ ~ lava 7}"])))

(defn demunge-cmd
  [cmd]
  (butlast
   (map (comp str/trim second)
        (re-seq #",\s*Command\s*:([^}]*)" cmd))))

(defmain mung
  [in]
  (let [cmds (str/split-lines (slurp in))]
    (println (munge-cmds cmds))))

(defmain demung
  [in]
  (let [munged-cmd (slurp in)]
    (doseq [cmd (demunge-cmd munged-cmd)]
      (println cmd))))
