package io.livinglayer.core

object LivingLayerVersion {
    const val name: String = "LivingLayer"
    const val version: String = "1.0.0-alpha02"
    const val packageName: String = "io.livinglayer"
}

enum class LivingLayerGeneration {
    V1ReactiveLayers,
    V2ShaderGraph,
    V3BackendAbstraction,
    V4LauncherEngine,
    V5AdaptiveInterfaces
}

data class LivingLayerCapabilities(
    val generations: Set<LivingLayerGeneration> = LivingLayerGeneration.entries.toSet(),
    val runtimeShaders: Boolean = true,
    val sensorDepth: Boolean = true,
    val launcherSurfaces: Boolean = true,
    val adaptiveInterfaces: Boolean = true
)
