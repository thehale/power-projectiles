<div align="center"> 

<img src="src/main/resources/icon.svg" width="256" height="256" />

# Power Projectiles
This is a Minecraft Plugin for Bukkit/Paper/Spigot servers that adds several new
super-powered projectiles that make the standard potion-tipped arrows look boring.

<!-- BADGES -->
[![](https://badgen.net/github/license/thehale/power-projectiles)](https://github.com/thehale/power-projectiles/blob/master/LICENSE)
[![](https://badgen.net/badge/icon/Sponsor/pink?icon=github&label)](https://github.com/sponsors/thehale)
[![](https://img.shields.io/badge/Follow-thehale-0A66C2?logo=linkedin)](https://www.linkedin.com/comm/mynetwork/discovery-see-all?usecase=PEOPLE_FOLLOWS&followMember=thehale)

</div>

## Crafting and Use
As long as a player has gotten the Enchanter advancement, he/she can also see 
the recipe for all Power Projectiles. Each projectile's lore (the text below its
name) contains instructions for how to use it.

## The Projectiles
| Name | Effect | Gif |
|------|--------|-----|
|💥 **Explosive Arrow** | Causes a small explosion on impact. | <img src="resources/ExplosiveArrow.gif" width="300" height="160" /> |
|🔥 **Forest Fire Arrow** | Starts a medium sized fire on impact. | <img src="resources/ForestFireArrow.gif" width="300" height="160" /> |
|🧟 **Horde Arrow** | Temporarily spawn several baby zombies. *NEW in v1.11.0!* | Example image not yet available |
|⛓ **Jail Arrow** | Surrounds a hit enemy with iron bars. *NEW in v1.8.0!* | ![Jail Arrow Example](https://user-images.githubusercontent.com/47901316/126048412-2e14253c-e66d-4263-928d-c38c6a9b981c.png) |
|⚡ **Lightning Arrow** | Summons a bolt of lightning at the site of impact. *NEW in v1.10.0!* | Example image not yet available |
|🕸 **Net Arrow** | Places cobwebs on impact to drastically slow down your enemies. | <img src="resources/NetArrow.gif" width="300" height="160" /> |
|🎯 **Sniper Arrow** | Ignores gravity and flies extra fast to directly hit targets in your crosshairs. | Example image not yet available |
|👥 **Swap Arrow** | Causes you and a hit enemy to trade locations on the map. | Example image not yet available |
|🤸‍♀️ **Teleport Arrow** | Teleports you to wherever it lands. | Example image not yet available |
|🕯 **Torch Arrow** | Places a torch wherever it lands. | <img src="resources/TorchArrow.gif" width="300" height="160" /> |

## Installation
1. Download the [latest release](https://github.com/thehale/power-projectiles/releases/) of Power Projectiles from GitHub Releases.
2. Copy the downloaded `power-projectiles-VERSION.jar` into your server's `plugins` directory.

## Contributing
 * Please report any issues you find on the `Issues` tab.
 * If you want to develop new features, feel free to open a pull request.

While developing, use the command `./gradlew runDevServer` to run a Paper server
with your changes loaded.

### Building Power Projectiles from Source
1. Download the source code: `git clone https://https://github.com/thehale/power-projectiles.git`.
2. Open a terminal in the root folder of the project.
3. Build the jar: `./gradlew build`.
4. Copy the generated jar from `./build/libs/power-projectiles.jar` into your server's `plugins` directory.

### Adding new Power Projectiles
Simply inherit from Power Arrow and implement the required abstract
methods to make your own Power Arrow!

Other types of Power Projectiles will come in the future.
