package me.plony.compose.minestom.internal

import me.plony.compose.minestom.SlotRange
import net.minestom.server.inventory.Inventory

public interface RenderContext {
    public val inventory: Inventory
    public val slotRange: SlotRange
}