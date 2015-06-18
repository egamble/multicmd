Munges several Minecraft commands into a single command (using ugly and ridiculous hackery) suitable for use in a command block.

Also demunges.

### Motivation

Because command blocks obstinately continue to fail to accept multiple commands. Making `n` command blocks to run `n` commands is less fun than most fun things. For example, it's less fun than [Black Tuesday](http://www.thebruery.com/beer/black-tuesday/).

### Usage

`lein run -m multicmd.run.mung cmds.json > munged.txt`

Munge the commands in `cmds.json` into a single command. `cmds.json` has the format `{"coords":"<x> <y> <z>","commands":["<first cmd>","<second cmd>",...]}`. `"coords"` specifies where a `MinecartCommandBlock` riding an activator rail will be summoned to execute the multiple commands. That location must be powered, for example by being on top of a redstone block. If `"coords"` is missing or `null`, the default location of `"~ ~1 ~"` will be used. 

`lein run -m multicmd.run.demung munged.txt > cmds.json`

Demunge a single munged command into its constituent commands. The output is in the form of a JSON suitable for use with `multicmd.run.mung`.


### History

#### Version 1.0.1:

Input to `multicmd.run.mung` is now a JSON. The default location of `"~ ~1 ~"` can now be overridden.

#### Version 1.0.0:

Initial version.
