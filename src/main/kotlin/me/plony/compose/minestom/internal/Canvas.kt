package me.plony.compose.minestom.internal

import androidx.compose.runtime.Composition
import net.minestom.server.inventory.Inventory

data class Canvas(val inventory: Inventory, val composition: Composition)