Munges several Minecraft commands into a single command (using ugly and ridiculous hackery) suitable for use in a command block.

Also demunges.

### Motivation

Originally I made this because command blocks don't accept multiple commands. This is less useful now that Minecraft has functions.

### Usage

`lein run -m multicmd.run.mung cmds.json > munged.txt`

Munge the commands in `cmds.json` into a single command. `cmds.json` has the format `{"coords":"<x> <y> <z>","commands":["<first cmd>","<second cmd>",...]}`. `"coords"` specifies where a `MinecartCommandBlock` riding an activator rail will be summoned to execute the multiple commands. That location must be powered, for example by being on top of a redstone block. If `"coords"` is missing or `null`, the default location of `"~ ~1 ~"` will be used. 

`lein run -m multicmd.run.demung munged.txt > cmds.json`

Demunge a single munged command into its constituent commands. The output is in the form of a JSON suitable for use with `multicmd.run.mung`.


### History

#### Version 1.0.2:

Now works with and requires MineCraft 1.9 or higher.

#### Version 1.0.1:

Input to `multicmd.run.mung` is now a JSON. The default location of `"~ ~1 ~"` can now be overridden.

#### Version 1.0.0:

Initial version.
