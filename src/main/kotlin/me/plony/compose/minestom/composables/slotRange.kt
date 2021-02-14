package me.plony.compose.minestom.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import me.plony.compose.minestom.components.SlotsComponent
import me.plony.compose.minestom.internal.MinestomApplier
import me.plony.compose.minestom.internal.RenderContext
import me.plony.compose.minestom.internal.RenderContextImpl

@Composable
fun RenderContext.column(column: Int, block: @Composable RenderContext.() -> Unit) {
    slotRange(column..column, slotRange.rows, block)
}

@Composable
fun RenderContext.row(row: Int, block: @Composable RenderContext.() -> Unit) {
    slotRange(slotRange.columns, row..row, block)
}

@Composable
fun RenderContext.slotRange(columns: IntRange, rows: IntRange, block: @Composable RenderContext.() -> Unit) {
    val slotRange = slotRange.subSlots(columns, rows)
    val newContext = RenderContextImpl(inventory, slotRange)
    ComposeNode<SlotsComponent, MinestomApplier>(
        factory = { SlotsComponent(inventory, newContext) },
        update = {
            set(newContext) { context = newContext }
        },
        content = { newContext.block() }
    )
}
