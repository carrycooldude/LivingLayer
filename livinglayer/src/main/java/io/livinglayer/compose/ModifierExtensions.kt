package io.livinglayer.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color

fun Modifier.livingGlow(
    enabled: Boolean = true,
    color: Color = Color(0xFF58E6FF),
    alpha: Float = 0.18f
): Modifier {
    if (!enabled) return this

    return drawWithContent {
        drawCircle(
            color = color.copy(alpha = alpha),
            radius = size.minDimension * 0.56f,
            center = center
        )
        drawContent()
    }
}

