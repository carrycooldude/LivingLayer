package io.livinglayer.effect

import io.livinglayer.core.Layer

class RefractionEffect(
    val strength: Float = 0.1f
) : Effect {
    override fun apply(layer: Layer) = Unit
}

