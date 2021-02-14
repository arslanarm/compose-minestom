package me.plony.compose.minestom.components

import me.plony.compose.minestom.internal.RenderContext
import me.plony.compose.minestom.internal.RenderContextImpl
import me.plony.compose.minestom.SlotRange
import net.minestom.server.inventory.Inventory

public class InventoryComponent(private val inventory: Inventory) : Component() {
    override val context: RenderContext = RenderContextImpl(inventory, SlotRange(0..8, 0..(inventory.size - 1) / 9))
    override fun clear() {
        clearChildren()
        inventory.clear()
    }
}