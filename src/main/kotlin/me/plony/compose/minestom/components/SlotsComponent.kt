package me.plony.compose.minestom.components

import me.plony.compose.minestom.internal.RenderContext
import me.plony.compose.minestom.internal.RenderContextImpl
import me.plony.compose.minestom.SlotRange
import net.minestom.server.inventory.Inventory

public class SlotsComponent(override var context: RenderContext): Component() {
    override fun clear() {
        clearChildren()
    }
}