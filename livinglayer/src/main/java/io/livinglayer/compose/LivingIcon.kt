package io.livinglayer.compose

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.livinglayer.core.LivingLayerConfig
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun LivingIcon(
    icon: Painter,
    modifier: Modifier = Modifier.size(96.dp),
    config: LivingLayerConfig = LivingLayerConfig()
) {
    val motion = rememberLivingMotion(enabled = config.gyroscope)
    val transition = rememberInfiniteTransition(label = "living-layer-motion")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = (PI.toFloat() * 2f),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "living-layer-phase"
    )

    Canvas(modifier = modifier) {
        val depthScale = (config.depth / 100f).coerceIn(0f, 0.36f)
        val iconSize = Size(size.width * (0.72f + depthScale), size.height * (0.72f + depthScale))
        val idleX = sin(phase) * size.width * 0.018f
        val idleY = sin(phase + PI.toFloat() / 2f) * size.height * 0.014f
        val parallaxX = (motion.x * size.width * 0.055f) + idleX
        val parallaxY = (motion.y * size.height * 0.055f) + idleY
        val highlightX = center.x - size.width * 0.18f + parallaxX * 1.8f
        val highlightY = center.y - size.height * 0.2f + parallaxY * 1.8f
        val left = center.x - iconSize.width / 2f + parallaxX
        val top = center.y - iconSize.height / 2f + parallaxY

        if (config.reflections) {
            drawCircle(
                color = Color.White.copy(alpha = 0.08f + (sin(phase) * 0.025f)),
                radius = size.minDimension * 0.52f,
                center = center.copy(
                    x = center.x + parallaxX * 0.6f,
                    y = center.y - size.height * 0.08f + parallaxY * 0.6f
                )
            )
        }

        if (config.glow) {
            drawCircle(
                color = Color(0xFF58E6FF).copy(alpha = 0.2f + (sin(phase) * 0.04f)),
                radius = size.minDimension * (0.44f + (sin(phase) * 0.025f)),
                center = center.copy(x = center.x + parallaxX * 0.35f, y = center.y + parallaxY * 0.35f)
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

        if (config.bloom) {
            drawCircle(
                color = Color.White.copy(alpha = 0.16f),
                radius = size.minDimension * 0.1f,
                center = center.copy(x = highlightX, y = highlightY)
            )
        }
    }
}

@Composable
private fun rememberLivingMotion(enabled: Boolean): LivingMotion {
    val context = LocalContext.current
    var x by remember { mutableFloatStateOf(0f) }
    var y by remember { mutableFloatStateOf(0f) }

    DisposableEffect(context, enabled) {
        if (!enabled) {
            x = 0f
            y = 0f
            return@DisposableEffect onDispose {}
        }

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                x = (-event.values.getOrNull(0).orZero() / 9.81f).coerceIn(-1f, 1f)
                y = (event.values.getOrNull(1).orZero() / 9.81f).coerceIn(-1f, 1f)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }

        sensor?.let {
            sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_GAME)
        }

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return LivingMotion(x = x, y = y)
}

private data class LivingMotion(
    val x: Float,
    val y: Float
)

private fun Float?.orZero(): Float = this ?: 0f
