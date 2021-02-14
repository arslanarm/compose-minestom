package me.plony.compose.minestom.internal

import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.snapshots.ObserverHandle
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.*
import world.cepi.kstom.IOScope
import java.util.concurrent.Executors

/**
 * Object for handling snapshot changes
 * Taken from [compose-browser-demo](https://github.com/ShikaSD/compose-browser-demo/blob/main/prelude/src/main/kotlin/compose/web/internal/GlobalSnapshotManager.kt)
 */
internal object GlobalSnapshotManager {
    private var started = false
    private var commitPending = false
    private var removeWriteObserver: (ObserverHandle)? = null

    private val scheduleContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher() + SupervisorJob()

    fun ensureStarted() {
        if (!started) {
            started = true
            removeWriteObserver = Snapshot.registerGlobalWriteObserver(globalWriteObserver)
        }
    }

    private val globalWriteObserver: (Any) -> Unit = {
        // Race, but we don't care too much if we end up with multiple calls scheduled.
        if (!commitPending) {
            commitPending = true
            schedule {
                commitPending = false
                Snapshot.sendApplyNotifications()
            }
        }
    }

    private inline fun schedule(crossinline block: () -> Unit) {
        IOScope.launch(scheduleContext) { block() }
    }
}