# DebugStickPro

![DebugStickPro](https://github.com/TWME-TW/DebugStickPro/assets/65117253/ac4be41a-f527-4c9b-b43e-e79180d33168)

DebugStickPro gives trusted builders a practical way to inspect, edit, copy, and
temporarily freeze Minecraft block states. It provides the familiar debug-stick
workflow without requiring a vanilla debug stick and adds an action-bar preview,
copy mode, freeze mode, permissions, filters, localization, and protection-plugin
integration.

[Download the latest release](https://github.com/TWME-TW/DebugStickPro/releases) |
[Report a problem](https://github.com/TWME-TW/DebugStickPro/issues) |
[View the default configuration](src/main/resources/config.yml)

[Player Guide](#player-guide) | [Administrator Guide](#administrator-guide)

> DebugStickPro requires
> [PacketEvents](https://github.com/retrooper/packetevents) 2.13.0 or later.
>
> The plugin is under active development. Test updates on a non-production server
> and report reproducible problems on the issue tracker.

## What you can do

- See the block-state properties of the block you are looking at in the action bar.
- Select and change one property at a time in Classic mode.
- Copy compatible properties from one block and apply them to other blocks.
- Temporarily freeze blocks so physics and neighboring updates do not change them.
- Use the plugin in English or Traditional Chinese, selected from each player's
  client locale.
- Let existing region-protection plugins decide where a player may make changes.
- Record Classic and Copy mode changes through CoreProtect when it is installed.

## Player Guide

### Get started

1. Ask an administrator for a Debug Stick, or run `/dsp give` if you have permission.
2. Hold the Debug Stick in your main hand.
3. Look at a block within five blocks. Its editable properties appear in the action
   bar.
4. Press your **Swap Item With Offhand** key (`F` by default) to move to the next
   mode. Sneak while pressing it to move to the previous mode.

You can also hold the stick and run `/dsp mode <classic|copy|freeze>` to select a
mode directly. Modes that you do not have permission to use are skipped.

### Controls

| Mode | Left click | Right click |
| --- | --- | --- |
| **Classic** | Select the next editable property. | Cycle the selected property's value. |
| **Copy** | Copy all supported block-state properties from the targeted block. | Apply matching copied properties to the targeted block. |
| **Freeze** | Unfreeze every block that you froze. | Freeze or unfreeze the targeted block. |

Copy mode only applies properties shared by the source and destination. It does not
replace the destination block's material or copy container contents.

Freeze mode is temporary. Frozen blocks are restored when their owner disconnects,
when the plugin is disabled, or when an administrator reloads DebugStickPro. It is
not a persistent block-lock system.

### Mode previews

#### Classic mode

![Classic mode action bar](https://github.com/TWME-TW/DebugStickPro/assets/65117253/1410849c-3648-435c-85ad-cabafa16153a)

#### Copy mode

![Copy mode action bar](https://github.com/TWME-TW/DebugStickPro/assets/65117253/d244bcfe-25a2-44df-bd1e-5b0f36d2e8f1)

#### Freeze mode

![Freeze mode action bar](https://github.com/TWME-TW/DebugStickPro/assets/65117253/ae7572c8-a015-4890-87bc-fc08445ec41a)

### Player commands

`/debugstickpro` and `/dsp` are aliases. Square brackets indicate an optional
argument; angle brackets indicate a required argument.

| Command | What it does |
| --- | --- |
| `/dsp` or `/dsp help` | Show the command list. |
| `/dsp give` | Give yourself a Debug Stick. |
| `/dsp give <player>` | Give an online player a Debug Stick. |
| `/dsp mode <mode>` | Change the held stick to Classic, Copy, or Freeze mode. |

The server owner decides which commands and modes players may use.

## Administrator Guide

### Requirements and supported servers

| Component | Requirement |
| --- | --- |
| Server software | Spigot, Paper, or Folia from Minecraft 1.19.4 through 26.2. Paper is the primary and recommended platform. |
| Java runtime | The plugin targets Java 17. Run the Java version required by your chosen Minecraft server release. |
| Required plugin | [PacketEvents](https://github.com/retrooper/packetevents) 2.13.0 or later. |
| Optional plugins | CoreProtect and PlaceholderAPI. |

VirtualEntities is bundled inside DebugStickPro and does not need to be installed
separately.

### Installation

1. Stop the server.
2. Download PacketEvents and place its JAR in the server's `plugins` directory.
3. Download DebugStickPro from the
   [GitHub Releases page](https://github.com/TWME-TW/DebugStickPro/releases) and
   place its JAR in the same directory.
4. Start the server and confirm that PacketEvents and DebugStickPro both enable
   without errors.
5. Grant the required permissions, then use `/dsp give <player>` to issue a stick.

Use a full server restart when installing or updating either plugin. Bukkit's global
`/reload` is not supported and may leave packet or display state inconsistent.

When upgrading DebugStickPro, back up `plugins/DebugStickPro/config.yml` and the
`lang` directory before replacing the JAR. On startup, missing settings and missing
language entries are added from the bundled defaults while existing values are
preserved.

### Administrator commands

| Command | Sender | Permission | What it does |
| --- | --- | --- | --- |
| `/dsp help` | Player or console | `debugstickpro.help` | Show the command list. |
| `/dsp give` | Player | `debugstickpro.give` | Give the sender a Debug Stick. |
| `/dsp give <player>` | Player or console | `debugstickpro.give` | Give an online player a Debug Stick. |
| `/dsp mode <mode>` | Player | `debugstickpro.mode` plus the mode permission | Change the held stick to Classic, Copy, or Freeze mode. |
| `/dsp reload` | Player or console | `debugstickpro.reload` | Reload DebugStickPro's configuration and languages and release frozen blocks. |

### Permissions

`debugstickpro.help` is granted to everyone by default. Every other declared
permission defaults to server operators.

| Permission | Purpose |
| --- | --- |
| `debugstickpro.basic` | Recommended parent node for normal trusted users; includes use, give, mode, Copy, Freeze, and help permissions. |
| `debugstickpro.admin` | Full access; includes `debugstickpro.basic`, reload, and all bypass permissions. |
| `debugstickpro.help` | View command help. |
| `debugstickpro.use` | Hold and interact with a Debug Stick and see its action-bar display. |
| `debugstickpro.give` | Use the give command for yourself or an online player. |
| `debugstickpro.give.other` | Parent node that includes `debugstickpro.give`, provided for permission-group organization. |
| `debugstickpro.mode` | Use the `/dsp mode` command. |
| `debugstickpro.mode.copy` | Enter and use Copy mode. |
| `debugstickpro.mode.freeze` | Enter and use Freeze mode. |
| `debugstickpro.bypassregion` | Ignore automatic region-protection checks. |
| `debugstickpro.bypassblockfilter` | Ignore both block material allowlists and denylists. |
| `debugstickpro.bypassblacklist` | Ignore the entire block-data property blacklist. |
| `debugstickpro.bypassblacklist.<type>` | Ignore one block-data property blacklist entry, for example `debugstickpro.bypassblacklist.waterloggeddata`. |

Grant `debugstickpro.basic` only to players who are allowed to modify block states.
These changes can create normally unobtainable or unsafe block combinations.

### Configuration

The generated configuration is `plugins/DebugStickPro/config.yml`. The
[default config](src/main/resources/config.yml) contains comments and all available
keys. Do not edit `ConfigVersion`; DebugStickPro uses it when upgrading the file.

| Section | What it controls |
| --- | --- |
| `Language` | Default locale and the locale files eligible for automatic per-player selection. |
| `ActionBarDisplay` | Property centering and the action-bar refresh interval in ticks. |
| `DebugStickItem` | Material, MiniMessage display name and lore, and per-mode custom model data. |
| `WhitelistWorlds` | Worlds where the stick may be used when the allowlist is enabled. `*` allows every world. |
| `BlacklistWorlds` | Worlds where the stick cannot be used. This list takes precedence over `WhitelistWorlds`. |
| `AutoRegionProtection` | Whether DebugStickPro asks other plugins if the player may build at the target. |
| `BlockDataFilter` | Which block-state property types are visible and editable. The blacklist takes precedence over the whitelist. |
| `BlockFilter` | Which block materials may be targeted. The blacklist takes precedence over the whitelist. |
| `ModeSetting` | Whether selected, copied, or frozen state is cleared when a player leaves a mode. |

Material names must match Bukkit's
[Material enum](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html).
Block-data filter names come from DebugStickPro's
[supported property types](src/main/java/dev/twme/debugstickpro/blockdatautil/subdata).

`BlockDataFilter.AllowUnsafeBisectedData` is disabled by default. Enabling it exposes
the `half` property of doors, double plants, and other multi-block structures; this
can create invalid pairs or duplicate drops.

After changing the configuration, run `/dsp reload`. If you change
`DebugStickItem.Material` or its custom model data, issue new sticks so players do
not keep items created under the old settings.

### Languages

English (`en_US`) and Traditional Chinese (`zh_TW`) are bundled. For each player,
DebugStickPro normalizes the client locale and uses the matching entry from
`Language.LangFiles`; otherwise it falls back to `Language.DefaultLanguage`.

To add a translation:

1. Copy `plugins/DebugStickPro/lang/en_US.yml` to a locale-named file such as
   `de_DE.yml`.
2. Translate the values without changing the YAML keys or MiniMessage tags.
3. Add `de_DE` to `Language.LangFiles` in `config.yml`.
4. Run `/dsp reload`.

Missing entries in existing language files are filled from the bundled locale or,
for custom locales, from English. Existing translations are preserved.

### Integrations and protection

- **PacketEvents** is required for Freeze mode's packet and display behavior.
- **CoreProtect** is optional. When a compatible CoreProtect API is available,
  Classic and Copy mode changes are recorded as block changes.
- **PlaceholderAPI** is optional. Installed placeholders are expanded in localized
  messages before they are sent to players.
- **Automatic region protection** asks the server's event system whether a player
  can place at the target location. Protection plugins that cancel that placement
  check can therefore deny DebugStickPro changes. Disable this behavior with
  `AutoRegionProtection.Enabled` only when another access-control strategy is in
  place.
- [Nether-No-Water](https://www.spigotmc.org/resources/dsp-add-on-nether-no-water.118723/)
  is an optional add-on that prevents players from changing the `waterlogged`
  property in the Nether.

### Troubleshooting

| Symptom | Check |
| --- | --- |
| DebugStickPro does not enable | Confirm PacketEvents 2.13.0 or later is installed and enabled first. Check that the server and Java versions match the support table. |
| The stick does nothing | Hold a plugin-issued stick in the main hand; verify `debugstickpro.use`, world lists, block filters, and region protection. |
| Copy or Freeze mode is skipped | Grant `debugstickpro.mode.copy` or `debugstickpro.mode.freeze`. Grant `debugstickpro.mode` as well when the player uses the mode command. |
| A property is missing | Check `BlockDataFilter`, its per-type bypass permission, and `AllowUnsafeBisectedData`. Some properties are intentionally unavailable for safety. |
| A player's language is not selected | Confirm the normalized locale file exists, is listed in `Language.LangFiles`, and contains valid YAML. |
| Existing sticks stop working after an item config change | Reissue them with `/dsp give`; recognition uses the currently configured material. |
| Frozen blocks need to be released | The player can left-click in Freeze mode, or an administrator can run `/dsp reload`. |

Known limitation: players must sneak to change a candle's `lit` value.

### Compatibility testing

Continuous integration builds the plugin and starts every stable Paper and Folia
release covered by the support range. Representative 1.19, 1.20, and 1.21 protocol
versions also run in-game command, item, display, locale, and mode tests. Spigot
protocol checkpoints are tested separately with the official BuildTools. Paper
remains the primary platform.

## Building from source

Building requires JDK 25; the produced plugin JAR targets Java 17.

```bash
mvn -B package
```

The shaded plugin is written to `target/DebugStickPro-<version>.jar`. Development
artifacts are also available from the
[TWME snapshot repository](https://repo.twme.dev/#/snapshots/dev/twme/DebugStickPro).
