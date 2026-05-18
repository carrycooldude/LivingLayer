package io.livinglayer.effect

import io.livinglayer.core.Layer

class FresnelEffect(
    val power: Float = 2f,
    val intensity: Float = 0.45f
) : Effect {
    override fun apply(layer: Layer) = Unit
}

