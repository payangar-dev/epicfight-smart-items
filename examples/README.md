# Epic Fight Smart Items - Config Examples

Copy any of these files to your `config/` folder and rename to `epicfight-smart-items.json`.

## Config Format

Each item selector can have:
- `id` - Item ID (e.g., `"minecraft:diamond_pickaxe"`)
- `tag` - Item tag (e.g., `"minecraft:pickaxes"`)
- `components` - Data components to match (Minecraft 1.21+ format)

All conditions use AND logic. For OR logic, add multiple entries to the list.

## Components Format

The `components` field uses Minecraft's native DataComponentPredicate format.
You can match any data component that items can have:

### Common Components

| Component | Example |
|-----------|---------|
| `minecraft:custom_name` | `"\"My Item\""` (JSON text component) |
| `minecraft:damage` | `0` (integer) |
| `minecraft:max_damage` | `1561` (integer) |
| `minecraft:unbreakable` | `{}` |
| `minecraft:enchantments` | `{"levels": {"minecraft:sharpness": 5}}` |
| `minecraft:custom_model_data` | `1234` |
| `minecraft:lore` | `["\"Line 1\"", "\"Line 2\""]` |

### Creating Test Items

Use the `/give` command to create items with components:

```
/give @p minecraft:book[custom_name="Mining Manual"]
/give @p minecraft:diamond_pickaxe[damage=0,unbreakable={}]
/give @p minecraft:diamond_sword[enchantments={levels:{sharpness:5}}]
```
