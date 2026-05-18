package io.livinglayer.effect

import io.livinglayer.core.Layer

class BlurEffect(
    val radius: Float = 16f
) : Effect {
    override fun apply(layer: Layer) = Unit
}

