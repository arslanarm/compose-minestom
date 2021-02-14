package me.plony.compose.minestom.internal

import androidx.compose.runtime.AbstractApplier
import me.plony.compose.minestom.components.Component

internal class MinestomApplier(root: Component) : AbstractApplier<Component>(root) {

    override fun onClear() {
        root.clearChildren()
    }

    override fun move(from: Int, to: Int, count: Int) {
        current.children.run {
            repeat(count) {
                val element = get(from)
                add(to, element)
                removeAt(from)
            }
        }
    }

    override fun remove(index: Int, count: Int) {
        repeat(count) {
            current.children
                .removeAt(index)
                .clear()
        }
    }

    override fun insertBottomUp(index: Int, instance: Component) {
        current.children.add(index, instance)
    }

    override fun insertTopDown(index: Int, instance: Component) {

    }

}