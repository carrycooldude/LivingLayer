package io.livinglayer.backend

import androidx.compose.ui.graphics.drawscope.DrawScope
import io.livinglayer.core.LivingSurface

class VulkanBackend private constructor() : RenderBackend {
    override val id: String = "vulkan"
    override val capabilities = BackendCapabilities(
        runtimeShaders = false,
        shaderGraph = true,
        nativeGpuBackend = true,
        targetRefreshRate = 144
    )

    override fun render(scope: DrawScope, surface: LivingSurface) {
        error("VulkanBackend requires a native renderer artifact. Use ComposeRenderBackend in the base package.")
    }

    companion object {
        fun unavailable(): RenderBackend = object : RenderBackend {
            override val id: String = "vulkan-unavailable"
            override val capabilities = BackendCapabilities(
                runtimeShaders = false,
                shaderGraph = false,
                nativeGpuBackend = false,
                targetRefreshRate = 0
            )

            override fun render(scope: DrawScope, surface: LivingSurface) {
                error("Vulkan backend is not bundled in livinglayer:1.0.0-alpha02.")
            }
        }
    }
}
