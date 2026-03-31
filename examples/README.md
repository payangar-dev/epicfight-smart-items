# Epic Fight Smart Items - Config Examples

Copy any of these files to your `config/` folder and rename to `epicfight-smart-items.json`.

## Config Format

Each item selector can have:
- `id` - Item ID (e.g., `"minecraft:diamond_pickaxe"`)
- `tag` - Item tag (e.g., `"minecraft:pickaxes"`)
- `nbt` - NBT data to match (Minecraft 1.20.1 CompoundTag format)

All conditions use AND logic. For OR logic, add multiple entries to the list.

## NBT Format

The `nbt` field uses Minecraft's NBT CompoundTag format.
You can match any NBT data that items can have:

### Common NBT Tags

| Tag | Example |
|-----|---------|
| `display.Name` | `"{\"text\":\"My Item\"}"` (JSON text component) |
| `Damage` | `0` (integer) |
| `Unbreakable` | `1` (byte, 1 = true) |
| `Enchantments` | `[{"id": "minecraft:sharpness", "lvl": 5}]` |
| `CustomModelData` | `1234` |
| `display.Lore` | `["{\"text\":\"Line 1\"}", "{\"text\":\"Line 2\"}"]` |

### Creating Test Items

Use the `/give` command to create items with NBT:

```
/give @p minecraft:book{display:{Name:'{"text":"Mining Manual"}'}}
/give @p minecraft:diamond_pickaxe{Damage:0,Unbreakable:1b}
/give @p minecraft:diamond_sword{Enchantments:[{id:"minecraft:sharpness",lvl:5s}]}
```
