package me.plony.compose.minestom.internal

import me.plony.compose.minestom.SlotRange
import net.minestom.server.inventory.Inventory

public class RenderContextImpl(
    override val inventory: Inventory,
    override val slotRange: SlotRange
) : RenderContext