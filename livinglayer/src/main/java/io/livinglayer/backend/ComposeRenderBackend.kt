package io.livinglayer.backend

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import io.livinglayer.core.LivingSurface

class ComposeRenderBackend : RenderBackend {
    override val id: String = "compose"
    override val capabilities = BackendCapabilities(
        runtimeShaders = true,
        shaderGraph = true,
        nativeGpuBackend = false,
        targetRefreshRate = 120
    )

    override fun render(scope: DrawScope, surface: LivingSurface) {
        with(scope) {
            drawCircle(
                color = Color(0xFF58E6FF).copy(alpha = 0.12f),
                radius = size.minDimension * 0.5f,
                center = center
            )
        }
    }
}

