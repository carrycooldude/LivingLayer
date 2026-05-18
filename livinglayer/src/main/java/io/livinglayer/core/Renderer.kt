package io.livinglayer.core

import androidx.compose.ui.graphics.drawscope.DrawScope
import io.livinglayer.effect.Effect

class LivingRenderer(
    private val layers: List<Layer> = emptyList(),
    private val effects: List<Effect> = emptyList()
) {
    fun render(scope: DrawScope) {
        layers.forEach { layer ->
            effects.forEach { effect -> effect.apply(layer) }
            layer.render(scope)
        }
    }
}

