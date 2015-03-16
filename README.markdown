Munges several Minecraft commands into a single command (using ugly and ridiculous hackery) suitable for use in a command block.

Also demunges.

### Motivation

Because command blocks obstinately continue to fail to accept multiple commands. Making `n` command blocks to run `n` commands is less fun than most fun things. For example, it's less fun than [Black Tuesday](http://www.thebruery.com/beer/black-tuesday/).

### Usage

`lein run -m multicmd.run.mung cmds.txt > munged.txt`

Munge commands, one per line, into a single command.

`lein run -m multicmd.run.demung munged.txt > cmds.txt`

Demunge a single munged command into its constituent commands.


### History

#### Version 1.0.0:

Initial version.
