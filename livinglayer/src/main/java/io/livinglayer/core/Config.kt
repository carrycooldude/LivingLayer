package io.livinglayer.core

data class LivingLayerConfig(
    val glow: Boolean = true,
    val bloom: Boolean = true,
    val reflections: Boolean = true,
    val depth: Float = 20f,
    val gyroscope: Boolean = true,
    val shader: LivingShaderPreset = LivingShaderPreset.Glass,
    val adaptive: Boolean = false
)

enum class LivingShaderPreset {
    Glass,
    Hologram,
    Liquid,
    Neon
}

