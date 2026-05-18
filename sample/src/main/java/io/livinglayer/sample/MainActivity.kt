package io.livinglayer.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicText
import io.livinglayer.LivingLayer
import io.livinglayer.compose.LivingIcon
import io.livinglayer.core.LivingLayerConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LivingLayerSample()
        }
    }
}

@Composable
private fun LivingLayerSample() {
    val icon = LivingLayerPainter()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090B10))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicText(
                text = "LivingLayer",
                style = TextStyle(
                    color = Color(0xFFF4F7FB),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicText(
                text = "Tilt your phone. Package ${LivingLayer.version}",
                style = TextStyle(
                    color = Color(0xFF8BA3AF),
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DemoTile("Glass", icon, LivingLayerConfig(depth = 18f, shader = io.livinglayer.core.LivingShaderPreset.Glass))
                DemoTile("Neon", icon, LivingLayerConfig(depth = 30f, glow = true, bloom = true, shader = io.livinglayer.core.LivingShaderPreset.Neon))
                DemoTile("Holo", icon, LivingLayerConfig(depth = 24f, reflections = true, shader = io.livinglayer.core.LivingShaderPreset.Hologram))
            }
        }
    }
}

@Composable
private fun DemoTile(
    label: String,
    icon: Painter,
    config: LivingLayerConfig
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(104.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0x6658E6FF), Color(0x33B86CFF))
                    ),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            LivingIcon(
                icon = icon,
                modifier = Modifier.size(96.dp),
                config = config
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        BasicText(
            text = label,
            style = TextStyle(
                color = Color(0xFFD8E8EF),
                fontSize = 13.sp
            )
        )
    }
}

private class LivingLayerPainter : Painter() {
    override val intrinsicSize: Size = Size.Unspecified

    override fun DrawScope.onDraw() {
        val plateSize = size.minDimension * 0.92f
        val plateTopLeft = Offset(
            x = center.x - plateSize / 2f,
            y = center.y - plateSize / 2f
        )
        val corner = plateSize * 0.24f

        drawRoundRect(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF1A1F22), Color(0xFF050607), Color.Black),
                center = center - Offset(plateSize * 0.16f, plateSize * 0.18f),
                radius = plateSize * 0.86f
            ),
            topLeft = plateTopLeft,
            size = Size(plateSize, plateSize),
            cornerRadius = CornerRadius(corner, corner)
        )
        drawRoundRect(
            color = Color.White.copy(alpha = 0.18f),
            topLeft = plateTopLeft + Offset(plateSize * 0.03f, plateSize * 0.03f),
            size = Size(plateSize * 0.94f, plateSize * 0.94f),
            cornerRadius = CornerRadius(corner * 0.86f, corner * 0.86f),
            style = Stroke(width = plateSize * 0.018f)
        )

        val radius = size.minDimension * 0.34f
        val spherePath = Path().apply {
            addOval(
                Rect(
                    left = center.x - radius,
                    top = center.y - radius,
                    right = center.x + radius,
                    bottom = center.y + radius
                )
            )
        }

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFA6FFD0),
                    Color(0xFF1DB954),
                    Color(0xFF006B30),
                    Color(0xFF022813)
                ),
                center = center,
                radius = radius * 1.12f
            ),
            radius = radius,
            center = center
        )

        clipPath(spherePath) {
            drawDiscoTiles(radius)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.White.copy(alpha = 0.46f), Color.Transparent),
                    center = center - Offset(radius * 0.42f, radius * 0.2f),
                    radius = radius * 0.72f
                ),
                radius = radius,
                center = center
            )
        }

        drawCircle(
            color = Color.White.copy(alpha = 0.2f),
            radius = radius,
            center = center,
            style = Stroke(width = radius * 0.035f)
        )

        drawArc(
            color = Color.Black,
            startAngle = 197f,
            sweepAngle = 132f,
            useCenter = false,
            topLeft = Offset(center.x - radius * 0.58f, center.y - radius * 0.24f),
            size = Size(radius * 1.28f, radius * 0.72f),
            style = Stroke(width = radius * 0.16f, cap = StrokeCap.Round)
        )
        drawArc(
            color = Color.Black,
            startAngle = 202f,
            sweepAngle = 122f,
            useCenter = false,
            topLeft = Offset(center.x - radius * 0.49f, center.y + radius * 0.02f),
            size = Size(radius * 1.02f, radius * 0.52f),
            style = Stroke(width = radius * 0.13f, cap = StrokeCap.Round)
        )
        drawArc(
            color = Color.Black,
            startAngle = 205f,
            sweepAngle = 112f,
            useCenter = false,
            topLeft = Offset(center.x - radius * 0.38f, center.y + radius * 0.25f),
            size = Size(radius * 0.78f, radius * 0.38f),
            style = Stroke(width = radius * 0.1f, cap = StrokeCap.Round)
        )

        drawSparkle(center + Offset(radius * 0.8f, -radius * 0.78f), radius * 0.18f)
        drawSparkle(center + Offset(-radius * 0.82f, radius * 0.54f), radius * 0.16f)
    }

    private fun DrawScope.drawDiscoTiles(radius: Float) {
        val tile = radius * 0.18f
        val startX = center.x - radius * 0.95f
        val startY = center.y - radius * 0.86f
        val columns = 11
        val rows = 10

        repeat(rows) { row ->
            repeat(columns) { column ->
                val x = startX + column * tile * 0.92f + if (row % 2 == 0) 0f else tile * 0.42f
                val y = startY + row * tile * 0.78f
                val dx = (x + tile / 2f - center.x) / radius
                val dy = (y + tile / 2f - center.y) / radius
                val distance = dx * dx + dy * dy
                val alpha = (0.18f + (1f - distance).coerceIn(0f, 1f) * 0.36f)
                val color = when ((row + column) % 4) {
                    0 -> Color(0xFFE7FFF0).copy(alpha = alpha + 0.12f)
                    1 -> Color(0xFF8DF5B8).copy(alpha = alpha)
                    2 -> Color(0xFF0E8E44).copy(alpha = alpha + 0.08f)
                    else -> Color(0xFF04491F).copy(alpha = alpha + 0.06f)
                }

                drawRoundRect(
                    color = color,
                    topLeft = Offset(x, y),
                    size = Size(tile * 0.82f, tile * 0.66f),
                    cornerRadius = CornerRadius(tile * 0.05f, tile * 0.05f)
                )
            }
        }
    }

    private fun DrawScope.drawSparkle(position: Offset, radius: Float) {
        drawLine(
            color = Color.White.copy(alpha = 0.88f),
            start = position - Offset(radius, 0f),
            end = position + Offset(radius, 0f),
            strokeWidth = radius * 0.08f
        )
        drawLine(
            color = Color.White.copy(alpha = 0.88f),
            start = position - Offset(0f, radius),
            end = position + Offset(0f, radius),
            strokeWidth = radius * 0.08f
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.White, Color.Transparent),
                center = position,
                radius = radius
            ),
            radius = radius,
            center = position
        )
    }
}
