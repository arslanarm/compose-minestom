# Compose-Minestom

Based on Compose-Runtime, library for creating declartive UI inside the inventory.

### Examples:

```kotlin
  canvas(player) {
    var clicks by remember { mutableStateOf(0) }

    slot(0, 0) {
      itemStack(Material.STICK, amount = clicks + 1) { clicks++ }
    }
  }
```


```kotlin
  canvas(player) {
    row(1) {
      stick()
    }
  }

@Composable
fun stick() {
  slot(0, 0) {
    itemStack(Material.STICK)
  }
}
```
