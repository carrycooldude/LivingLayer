package io.livinglayer.backend

import androidx.compose.ui.graphics.drawscope.DrawScope
import io.livinglayer.core.LivingSurface

interface RenderBackend {
    val id: String
    val capabilities: BackendCapabilities

    fun render(scope: DrawScope, surface: LivingSurface)
}

data class BackendCapabilities(
    val runtimeShaders: Boolean,
    val shaderGraph: Boolean,
    val nativeGpuBackend: Boolean,
    val targetRefreshRate: Int
)

