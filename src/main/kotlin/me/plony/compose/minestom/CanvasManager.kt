package me.plony.compose.minestom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.DefaultMonotonicFrameClock
import androidx.compose.runtime.Recomposer
import kotlinx.coroutines.*
import me.plony.compose.minestom.components.InventoryComponent
import me.plony.compose.minestom.internal.Canvas
import me.plony.compose.minestom.internal.GlobalSnapshotManager
import me.plony.compose.minestom.internal.MinestomApplier
import me.plony.compose.minestom.internal.RenderContext
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import world.cepi.kstom.addEventCallback
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors

object CanvasManager {
    private val inventories = ConcurrentHashMap<UUID, Canvas>()

    private fun getOrCreate(player: UUID, block: @Composable RenderContext.() -> Unit): Canvas =
        inventories.getOrPut(player) {
            val inventory = Inventory(InventoryType.CHEST_6_ROW, "Test")
            Canvas(inventory, createComposition(inventory, block))
        }

    private fun createComposition(inventory: Inventory, block: @Composable RenderContext.() -> Unit): Composition {
        val context = DefaultMonotonicFrameClock + Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        val recomposer = Recomposer(context)
        val component = InventoryComponent(inventory)
        val composition = Composition(
            applier = MinestomApplier(component),
            parent = recomposer
        )
        composition.setContent { component.context.block() }

        CoroutineScope(context).launch(start = CoroutineStart.UNDISPATCHED) {
            recomposer.runRecomposeAndApplyChanges()
        }
        return composition
    }

    fun init() {
        GlobalSnapshotManager.ensureStarted()
        MinecraftServer.getGlobalEventHandler()
            .addEventCallback<InventoryPreClickEvent> {
                if (inventory in inventories.values.map { it.inventory })
                    isCancelled = true
            }
    }

    fun canvas(player: Player, block: @Composable RenderContext.() -> Unit): Composition {
        val (inventory, composition) = getOrCreate(player.uuid, block)
        player.openInventory(inventory)
        return composition
    }
}