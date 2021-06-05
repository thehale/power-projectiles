# Power Projectiles
This is a Minecraft Plugin for Bukkit/Paper/Spigot servers that adds several new
super-powered projectiles.

## Crafting and Use
As long as a player has gotten the Enchanter advancement, he/she can also see 
the recipe for all Power Projectiles. Each projectile's lore (the text below its
name) contains instructions for how to use it.

Don't worry, Power Projectiles still work like regular projectiles, so you can
use them to defend yourself from attacking mobs and players.

### Explosive Arrows

<img src="resources/ExplosiveArrow.gif" width="600" height="320" />

Add a little extra bang to your shot with arrows that create a mini TNT explosion
upon impact! Requires a Flame enchanted bow to work.

### Forest Fire Arrows

<img src="resources/ForestFireArrow.gif" width="600" height="320" />

Harness the power of the blaze rod to engulf your enemies in flames!
The Forest Fire Arrow uses a blaze rod instead of a stick and will start
a 3x3x3 fire around its impact site. No special enchantment required!

### Net Arrows

<img src="resources/NetArrow.gif" width="600" height="320" />

Drastically slow down your opponents by surrounding them with cobweb! 
Surround a normal arrow with cobweb to get a Net Arrow that can be used
with a regular bow!
If you're worried about not having enough cobweb to craft these, don't 
worry. This plugin also provides a recipe to craft cobweb.

### Torch Arrows

<img src="resources/TorchArrow.gif" width="600" height="320" />

Light up large caves or a dark countryside with Torch Arrows that place torches
where they land! Requires a Flame enchanted bow to work.

## Installation
1. Download the [latest release](https://github.com/jhale1805/power-projectiles/releases/) of Power Projectiles from GitHub Releases.
2. Copy the downloaded `power-projectiles-VERSION.jar` into your server's `plugins` directory.

## Contributing
 * Please report any issues you find on the `Issues` tab.
 * If you want to develop new features, feel free to open a pull request.

### Building Power Projectiles from Source
1. Download the source code: `git clone https://https://github.com/jhale1805/power-projectiles.git`.
2. Open a terminal in the root folder of the project.
3. Build the jar: `gradle build`.
4. Copy the generated jar from `./build/libs/power-projectiles.jar` into your server's `plugins` directory.

### Adding new Power Projectiles
Simply inherit from Power Arrow and implement the required abstract
methods to make your own Power Arrow!

Other types of Power Projectiles will come in the future.
