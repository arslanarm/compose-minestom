package me.plony.compose.minestom.internal

import androidx.compose.runtime.Composition
import net.minestom.server.inventory.Inventory

public data class Canvas(val inventory: Inventory, val composition: Composition)