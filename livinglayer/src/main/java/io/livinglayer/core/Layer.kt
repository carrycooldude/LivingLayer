package io.livinglayer.core

import androidx.compose.ui.graphics.drawscope.DrawScope

abstract class Layer {
    abstract fun render(scope: DrawScope)
}

