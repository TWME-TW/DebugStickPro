![DSP](https://github.com/TWME-TW/DebugStickPro/assets/65117253/ac4be41a-f527-4c9b-b43e-e79180d33168)

# DebugStickPro
> ### A powerful debugstick plugin
---
> **Warning**: This plugin is still in development and may have bugs. Please report any bugs you find to the [issue page](https://github.com/TWME-TW/DebugStickPro/issues)

> Support for Paper 1.19.4 ~ 1.20.4
---
## Features:
### Dynamically display block data:
 - Display the data of the block you are looking at in the action bar.
#### Classic Mode
<img width="960" alt="image" src="https://github.com/TWME-TW/DebugStickPro/assets/65117253/1410849c-3648-435c-85ad-cabafa16153a">

#### Copy Mode
<img width="958" alt="image" src="https://github.com/TWME-TW/DebugStickPro/assets/65117253/d244bcfe-25a2-44df-bd1e-5b0f36d2e8f1">

#### Freeze Mode
<img width="959" alt="image" src="https://github.com/TWME-TW/DebugStickPro/assets/65117253/ae7572c8-a015-4890-87bc-fc08445ec41a">

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

### Support filtering specific BlockData:
 - You can filter specific BlockData in the config file.

### Support for multiple languages.
 - Automatically select translation files based on the player's language. 
> Translation files for each language need to be translated manually beforehand.
## Configuration:
### Configuration File:
[config.yml](https://github.com/TWME-TW/DebugStickPro/blob/main/src/main/resources/config.yml)

### Language File:
[lang](https://github.com/TWME-TW/DebugStickPro/tree/main/src/main/resources/lang)
### Permissions:
[plugin.yml](https://github.com/TWME-TW/DebugStickPro/blob/main/src/main/resources/plugin.yml)

### Commands:
 > `/debugstickpro` = `/dsp` ≒ `/dsp help`
 - `/dsp help` Show help message.
 - `/dsp give [player]` Give a debug stick to a player.
 - `/dsp mode <mode>` Change the mode.
 - `/dsp reload` Reload the configuration file.
   
---

### Usage:
 - Use `/dsp give` to get a debug stick.
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
 - Press the `Swap Hand` key to switch mode. (Default: F)

## How to install:
1. Download the latest version of the plugin from the [release page](https://github.com/TWME-TW/DebugStickPro/releases).
2. Put the plugin into the `plugins` folder of your server.
3. Restart your server.
4. Enjoy!

## Latest Dev Version:
 - https://repo.twme.dev/#/snapshots/dev/twme/DebugStickPro

### Known Issues:
 - You have to sneak in order to change the Lit value of the candle.

