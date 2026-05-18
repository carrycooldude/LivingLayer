package io.livinglayer.effect

import io.livinglayer.core.Layer

interface Effect {
    fun apply(layer: Layer)
}

