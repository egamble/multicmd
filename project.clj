(defproject egamble/multicmd "1.0.2"
  :description "Munge multiple Minecraft commands into one command, for use in a command block."
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cheshire "5.4.0"]
                 [net.drib/defmain "0.1.0"]]
  :url "https://github.com/egamble/multicmd"
  :aot [multicmd.run])
