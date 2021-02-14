package me.plony.compose.minestom.components

import me.plony.compose.minestom.internal.RenderContext
import me.plony.compose.minestom.internal.RenderContextImpl
import me.plony.compose.minestom.SlotRange
import net.minestom.server.inventory.Inventory
import net.minestom.server.item.ItemStack
import kotlin.reflect.KProperty

public class SlotComponent(
    itemStack: ItemStack,
    context: RenderContext
) : Component() {
    override var context: RenderContext by update(context)

    override fun clear() {
        inventory.setItemStack(slot, ItemStack.getAirItem())
    }
    public val slot: Int
        get() = context.slotRange.run { rows.first * 9 + columns.first }
    public var itemStack: ItemStack by update(itemStack)
    private val inventory get() = context.inventory

    internal inline fun <reified T> update(value: T) = UpdateDelegate(value)

    internal class UpdateDelegate<T>(var value: T) {
        operator fun getValue(thisRef: SlotComponent, property: KProperty<*>): T = value
        operator fun setValue(thisRef: SlotComponent, property: KProperty<*>, value: T) = with(thisRef) {
            clear()
            this@UpdateDelegate.value = value
            inventory.setItemStack(slot, itemStack)
        }
    }
}