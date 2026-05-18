package io.livinglayer.effect

import io.livinglayer.core.Layer

class RippleEffect(
    val amplitude: Float = 0.12f
) : Effect {
    override fun apply(layer: Layer) = Unit
}

