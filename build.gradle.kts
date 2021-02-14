import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("kapt") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("org.jetbrains.compose") version "0.3.0-build152"
    `maven-publish`
    maven
}

group = "me.plony"
version = "0.1"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }

    // Use mavenCentral
    maven(url = "https://repo1.maven.org/maven2/")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://libraries.minecraft.net")
    maven(url = "https://jitpack.io")
    maven(url = "https://jcenter.bintray.com/")
}

dependencies {
    api(compose.runtime) { isTransitive = true }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")

    implementation("com.github.Minestom:Minestom:437afc28ff")
    implementation("com.github.Project-Cepi:KStom:120c4c5475")
}


tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
            useIR = true
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=androidx.compose.runtime.ExperimentalComposeApi"
            )
        }
    }
}

kotlin {
    explicitApi()
}