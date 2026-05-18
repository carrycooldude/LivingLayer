package io.livinglayer.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import io.livinglayer.core.LivingLayerConfig

@Composable
fun LivingIcon(
    icon: Painter,
    modifier: Modifier = Modifier.size(96.dp),
    config: LivingLayerConfig = LivingLayerConfig()
) {
    Canvas(modifier = modifier) {
        val depthScale = (config.depth / 100f).coerceIn(0f, 0.36f)
        val iconSize = Size(size.width * (0.72f + depthScale), size.height * (0.72f + depthScale))
        val left = center.x - iconSize.width / 2f
        val top = center.y - iconSize.height / 2f

        if (config.reflections) {
            drawCircle(
                color = Color.White.copy(alpha = 0.08f),
                radius = size.minDimension * 0.52f,
                center = center.copy(y = center.y - size.height * 0.08f)
            )
        }

        if (config.glow) {
            drawCircle(
                color = Color(0xFF58E6FF).copy(alpha = 0.22f),
                radius = size.minDimension * 0.46f,
                center = center
            )
        }

        translate(left = left, top = top) {
            with(icon) {
                draw(
                    size = iconSize,
                    alpha = 1f,
                    colorFilter = null
                )
            }
        }
    }
}
