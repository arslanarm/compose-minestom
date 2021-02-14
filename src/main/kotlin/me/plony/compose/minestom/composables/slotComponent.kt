package me.plony.compose.minestom.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import me.plony.compose.minestom.components.SlotComponent
import me.plony.compose.minestom.internal.MinestomApplier
import me.plony.compose.minestom.internal.RenderContext
import me.plony.compose.minestom.internal.RenderContextImpl
import net.minestom.server.entity.Player
import net.minestom.server.inventory.click.ClickType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

@Composable
fun RenderContext.slot(x: Int, y: Int, itemStackProvider: () -> ItemStack) {
    val itemStack = itemStackProvider()
    require(slotRange.columns.first + x in slotRange.columns)
    require(slotRange.rows.first + y in slotRange.rows)
    val newContext = RenderContextImpl(
        inventory,
        slotRange.subSlots(x..x, y..y)
    )
    ComposeNode<SlotComponent, MinestomApplier>(
        factory = { SlotComponent(inventory, itemStack, newContext) },
        update = {
            set(newContext, SlotComponent::context::set)
            set(itemStack, SlotComponent::itemStack::set)
        }
    )
}

inline fun itemStack(
    material: Material,
    amount: Byte = 1,
    crossinline onClick: (player: Player) -> Unit = { _-> }
) = object : ItemStack(material, amount) {
    override fun onInventoryClick(player: Player, clickType: ClickType, slot: Int, playerInventory: Boolean) {
        if (clickType == ClickType.LEFT_CLICK)
            onClick(player)
    }
}