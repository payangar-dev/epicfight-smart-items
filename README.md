# Epic Fight Smart Items

A Minecraft mod that automatically switches between Epic Fight's combat and mining modes based on the item you're holding.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1+
- [Epic Fight](https://modrinth.com/mod/epic-fight) mod

## How It Works

When you select an item in your hotbar:
- **Item matches your config** → Switches to **Mining Mode** (vanilla animations)
- **Item doesn't match** → Switches back to **Combat Mode** (Epic Fight animations)

This is the same as pressing the `R` key manually, but automatic!

## Installation

1. Install NeoForge 1.21.1
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
| `components` | Match data components | See below |

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
    { "tag": "c:tools/hammers" }
  ]
}
```

### Matching by Components (Advanced)

You can match items based on their data components (custom name, enchantments, damage, etc.):

```json
{
  "items": [
    {
      "id": "minecraft:book",
      "components": {
        "minecraft:custom_name": "\"Mining Manual\""
      }
    },
    {
      "id": "minecraft:diamond_pickaxe",
      "components": {
        "minecraft:unbreakable": {}
      }
    },
    {
      "id": "minecraft:diamond_sword",
      "components": {
        "minecraft:enchantments": {
          "levels": {
            "minecraft:sharpness": 5
          }
        }
      }
    }
  ]
}
```

#### Common Components

| Component | Description | Example Value |
|-----------|-------------|---------------|
| `minecraft:custom_name` | Item's custom name | `"\"My Pickaxe\""` |
| `minecraft:damage` | Current damage value | `0` |
| `minecraft:unbreakable` | Unbreakable flag | `{}` |
| `minecraft:enchantments` | Enchantments | `{"levels": {"minecraft:efficiency": 5}}` |
| `minecraft:custom_model_data` | Custom model data | `1234` |

### Testing Items

Use the `/give` command to create items with specific components:

```
/give @p minecraft:book[custom_name="Mining Manual"]
/give @p minecraft:diamond_pickaxe[unbreakable={}]
/give @p minecraft:stick[custom_name="Mining Wand"]
```

## Examples

Check the [`examples/`](examples/) folder for more configuration examples:

- `mining-tools.json` - All vanilla mining tools
- `using-tags.json` - Using item tags
- `combined-conditions.json` - Combining ID with components
- `modded-items.json` - Examples for modded items

## License

All Rights Reserved

## Credits

- [Epic Fight](https://modrinth.com/mod/epic-fight) by Yesman
