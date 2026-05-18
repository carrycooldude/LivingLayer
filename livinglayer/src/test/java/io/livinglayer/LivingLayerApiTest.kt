package io.livinglayer

import io.livinglayer.adaptive.AdaptiveInterfaceEngine
import io.livinglayer.adaptive.AdaptiveInterfaceRequest
import io.livinglayer.adaptive.InterfaceContext
import io.livinglayer.core.LivingLayer
import io.livinglayer.core.LivingLayerGeneration
import io.livinglayer.core.LivingLayerVersion
import io.livinglayer.launcher.LauncherEngine
import io.livinglayer.launcher.LauncherItem
import io.livinglayer.launcher.LauncherViewport
import io.livinglayer.shader.ShaderNodeType
import io.livinglayer.shader.shaderGraph
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LivingLayerApiTest {
    @Test
    fun exposesPackageVersionAndCapabilities() {
        assertEquals("1.0.0-alpha01", LivingLayerVersion.version)
        assertTrue(io.livinglayer.LivingLayer.capabilities.generations.contains(LivingLayerGeneration.V5AdaptiveInterfaces))
    }

    @Test
    fun buildsFluentLayerSpec() {
        val layer = LivingLayer {
            glass()
            glow()
            bloom()
            fresnel()
        }

        assertEquals(5, layer.effects.size)
    }

    @Test
    fun buildsShaderGraph() {
        val graph = shaderGraph {
            val content = content()
            val glass = glass()
            val output = output()
            connect(content, glass)
            connect(glass, output)
        }

        assertEquals(3, graph.nodes.size)
        assertEquals(2, graph.edges.size)
        assertEquals(ShaderNodeType.Output, graph.nodes.last().type)
    }

    @Test
    fun resolvesLauncherScene() {
        val layer = LivingLayer { glass() }
        val scene = LauncherEngine().resolveLayout(
            items = List(7) { index ->
                LauncherItem(id = "app-$index", label = "App $index", layer = layer)
            },
            viewport = LauncherViewport(widthDp = 420, heightDp = 860)
        )

        assertEquals(4, scene.columns)
        assertEquals(7, scene.cells.size)
        assertEquals(1, scene.cells.last().row)
    }

    @Test
    fun generatesAdaptiveInterface() {
        val adaptive = AdaptiveInterfaceEngine().generate(
            AdaptiveInterfaceRequest(
                id = "focus-card",
                context = InterfaceContext.Focused,
                motionAware = true,
                holographic = true
            )
        )

        assertEquals("focus-card", adaptive.surface.id)
        assertTrue(adaptive.surface.config.adaptive)
        assertEquals(28f, adaptive.surface.config.depth)
    }
}
