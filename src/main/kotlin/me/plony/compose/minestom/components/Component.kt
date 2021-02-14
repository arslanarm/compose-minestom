package me.plony.compose.minestom.components

import me.plony.compose.minestom.internal.RenderContext

public abstract class Component {
    public val children: MutableList<Component> = mutableListOf()
    public abstract val context: RenderContext

    public abstract fun clear()

    public fun clearChildren() {
        children.forEach(Component::clear)
        children.clear()
    }
}