package me.plony.compose.minestom.internal

import me.plony.compose.minestom.SlotRange
import net.minestom.server.inventory.Inventory

interface RenderContext {
    val inventory: Inventory
    val slotRange: SlotRange
}