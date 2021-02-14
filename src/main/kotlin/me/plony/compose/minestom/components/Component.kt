package me.plony.compose.minestom.components

import me.plony.compose.minestom.internal.RenderContext

abstract class Component {
    val children = mutableListOf<Component>()
    abstract val context: RenderContext

    abstract fun clear()

    fun clearChildren() {
        children.forEach(Component::clear)
        children.clear()
    }
}