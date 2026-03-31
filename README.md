# Epic Fight Smart Items

A Minecraft mod that automatically switches between Epic Fight's combat and mining modes based on the item you're holding.

## Requirements

- Minecraft 1.20.1
- Forge 47.4+
- [Epic Fight](https://modrinth.com/mod/epic-fight) mod (Forge 1.20.1)

## How It Works

When you select an item in your hotbar:
- **Item matches your config** → Switches to **Mining Mode** (vanilla animations)
- **Item doesn't match** → Switches back to **Combat Mode** (Epic Fight animations)

This is the same as pressing the `R` key manually, but automatic!

## Installation

1. Install Forge 1.20.1
2. Install Epic Fight mod
3. Download `epicfightsmartitems-x.x.x.jar` from [Releases](../../releases)
4. Place the jar in your `mods/` folder
5. Launch Minecraft

## Configuration

Create a file named `epicfight-smart-items.json` in your `config/` folder.

### Basic Example

```json
{
  "items": [
    { "tag": "minecraft:pickaxes" },
    { "tag": "minecraft:shovels" },
    { "tag": "minecraft:hoes" },
    { "id": "minecraft:torch" }
  ]
}
```

This config will switch to mining mode when holding any pickaxe, shovel, hoe, or torch.

### Config Options

Each item entry can have:

| Field | Description | Example |
|-------|-------------|---------|
| `id` | Match specific item | `"minecraft:diamond_pickaxe"` |
| `tag` | Match item tag | `"minecraft:pickaxes"` |
| `nbt` | Match NBT data | See below |

All conditions in a single entry use **AND** logic. Multiple entries use **OR** logic.

### Matching by Item ID

```json
{
  "items": [
    { "id": "minecraft:diamond_pickaxe" },
    { "id": "minecraft:netherite_shovel" }
  ]
}
```

### Matching by Tag

```json
{
  "items": [
    { "tag": "minecraft:pickaxes" },
    { "tag": "forge:tools/hammers" }
  ]
}
```

> **Note:** In Forge 1.20.1, common tags use the `forge:` namespace (e.g., `forge:tools/pickaxes`) instead of `c:`.

### Matching by NBT (Advanced)

You can match items based on their NBT data (custom name, enchantments, damage, etc.):

```json
{
  "items": [
    {
      "id": "minecraft:book",
      "nbt": {
        "display": {
          "Name": "{\"text\":\"Mining Manual\"}"
        }
      }
    },
    {
      "id": "minecraft:diamond_pickaxe",
      "nbt": {
        "Unbreakable": 1
      }
    },
    {
      "id": "minecraft:diamond_sword",
      "nbt": {
        "Enchantments": [
          {
            "id": "minecraft:sharpness",
            "lvl": 5
          }
        ]
      }
    }
  ]
}
```

#### Common NBT Tags

| Tag | Description | Example Value |
|-----|-------------|---------------|
| `display.Name` | Item's custom name (JSON text) | `"{\"text\":\"My Pickaxe\"}"` |
| `Damage` | Current damage value | `0` |
| `Unbreakable` | Unbreakable flag | `1` |
| `Enchantments` | Enchantments list | `[{"id": "minecraft:efficiency", "lvl": 5}]` |
| `CustomModelData` | Custom model data | `1234` |

### Testing Items

Use the `/give` command to create items with specific NBT:

```
/give @p minecraft:book{display:{Name:'{"text":"Mining Manual"}'}}
/give @p minecraft:diamond_pickaxe{Unbreakable:1b}
/give @p minecraft:stick{display:{Name:'{"text":"Mining Wand"}'}}
```

## Examples

Check the [`examples/`](examples/) folder for more configuration examples:

- `mining-tools.json` - All vanilla mining tools
- `using-tags.json` - Using item tags
- `combined-conditions.json` - Combining ID with NBT matching
- `modded-items.json` - Examples for modded items

## License

All Rights Reserved

## Credits

- [Epic Fight](https://modrinth.com/mod/epic-fight) by Yesman
