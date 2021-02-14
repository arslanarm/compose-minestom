# Compose-Minestom

Based on Compose-Runtime, library for creating declartive UI inside the inventory.

### Examples:

```kotlin
  canvas(player) {
    var clicks by remember { mutableStateOf(0) }

    slot(0, 0) {
      itemStack(Material.STICK, amount = (clicks + 1).toByte()) { clicks++ }
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

### Gradle

Library depends on [Minestom](https://github.com/Minestom/Minestom), [KStom](https://github.com/Project-Cepi/KStom), and [Compose-Desktop Runtime](https://github.com/JetBrains/compose-jb)

To add compose runtime use the plugin jetbrains provides
```
// setting.gradle.kts
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }
}

// build.gradle.kts
plugins {
    id("org.jetbrains.compose") version "0.3.0-build152"
}

repositories {
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.runtime)
}
```

## See also

[Canvas](https://github.com/mworzala/canvas/)

[compose-web-demo](https://github.com/ShikaSD/compose-browser-demo)
