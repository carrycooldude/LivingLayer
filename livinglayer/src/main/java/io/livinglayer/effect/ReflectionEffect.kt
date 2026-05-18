package io.livinglayer.effect

import io.livinglayer.core.Layer

class ReflectionEffect(
    val opacity: Float = 0.18f,
    val falloff: Float = 0.64f
) : Effect {
    override fun apply(layer: Layer) = Unit
}

