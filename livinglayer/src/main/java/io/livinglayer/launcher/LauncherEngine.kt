package io.livinglayer.launcher

import io.livinglayer.core.LivingLayerConfig
import io.livinglayer.core.LivingSurface
import io.livinglayer.core.LivingSurfaceState

class LauncherEngine(
    private val depthScale: Float = 1f
) {
    fun resolveLayout(items: List<LauncherItem>, viewport: LauncherViewport): LauncherScene {
        val columns = when {
            viewport.widthDp >= 840 -> 6
            viewport.widthDp >= 600 -> 5
            else -> 4
        }

        val cells = items.mapIndexed { index, item ->
            LauncherCell(
                item = item,
                column = index % columns,
                row = index / columns,
                surface = LivingSurface(
                    id = item.id,
                    layer = item.layer,
                    config = LivingLayerConfig(depth = 20f * depthScale),
                    state = LivingSurfaceState.Resting
                )
            )
        }

        return LauncherScene(columns = columns, cells = cells)
    }
}

data class LauncherViewport(
    val widthDp: Int,
    val heightDp: Int
)

data class LauncherItem(
    val id: String,
    val label: String,
    val layer: io.livinglayer.core.LivingLayerSpec
)

data class LauncherCell(
    val item: LauncherItem,
    val column: Int,
    val row: Int,
    val surface: LivingSurface
)

data class LauncherScene(
    val columns: Int,
    val cells: List<LauncherCell>
)

