# DebugStickPro
> ### A powerful debugstick plugin
---
> warning: This plugin is still in development and may have bugs. Please report any bugs you find to the [issue page](https://github.com/TWME-TW/DebugStickPro/issues)

> Support for Paper 1.20.4
---
## Features:
### Three Modes:
 - Classic Mode: The same as the original debug stick.
 - Copy Mode: Copy the data of a block and paste it onto other blocks.
 - Freeze Mode: Freeze the status of a block.

### Custom Item Support:
 - You can customize the item used as the debug stick in the config file.

### CoreProtect Log Support:
 - Support for CoreProtect, which can record the operation of the debug stick.

### Auto Region Protection Support:
 - Support for region protection plugins, such as WorldGuard, GriefPrevention, etc.
## Configuration:
### Configuration File:
[config.yml](https://github.com/TWME-TW/DebugStickPro/blob/main/src/main/resources/config.yml)

### Language File:
[lang.yml](https://github.com/TWME-TW/DebugStickPro/blob/main/src/main/resources/lang.yml)
### Permissions:
[plugin.yml](https://github.com/TWME-TW/DebugStickPro/blob/main/src/main/resources/plugin.yml)


--- 

### Usage:
#### Commands:
 > `/debugstickpro` = `/dsp` ≒ `/dsp help`
 - `/dsp help` Show help message.
 - `/dsp give [player]` Give a debug stick to a player.
 - `/dsp mode [mode]` Change the mode.
 - `/dsp reload` Reload the configuration file.
 - ``
#### Classic Mode:
Same as regular debugging stick operation: 
 - Left-click to select the desired data type to modify.
 - Right-click to change the value of that data.

#### Copy Mode:
 - Left-click to select the data of a block to be copied.
 - Right-click to paste that block's data onto other blocks.

#### Freeze Mode:
 >  The status of frozen blocks will not be updated.
 - Right-click to freeze/unfreeze a block.
 - Left-click to unfreeze all frozen blocks.
### How to change mode:
 - Press the `Swap Hand` key to switch mode.

## How to install:
1. Download the latest version of the plugin from the [release page](https://github.com/TWME-TW/DebugStickPro/releases).
2. Put the plugin into the `plugins` folder of your server.
3. Restart your server.
4. Enjoy!

### Known Issues:
 - You have to sneak in order to change the Lit value of the candle.

