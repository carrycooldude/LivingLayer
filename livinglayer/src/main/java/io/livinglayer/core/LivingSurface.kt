package io.livinglayer.core

data class LivingSurface(
    val id: String,
    val layer: LivingLayerSpec,
    val config: LivingLayerConfig = LivingLayerConfig(),
    val state: LivingSurfaceState = LivingSurfaceState.Resting
)

enum class LivingSurfaceState {
    Resting,
    Focused,
    Pressed,
    Expanded,
    Ambient
}

