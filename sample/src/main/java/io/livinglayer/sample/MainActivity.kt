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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
        val radius = size.minDimension * 0.38f
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF58E6FF), Color(0xFF6C7CFF), Color(0xFF11151C)),
                center = center,
                radius = radius * 1.25f
            ),
            radius = radius,
            center = center
        )
        drawCircle(
            color = Color.White.copy(alpha = 0.22f),
            radius = radius * 0.42f,
            center = center - Offset(radius * 0.32f, radius * 0.32f)
        )
        drawCircle(
            color = Color(0xFFB86CFF).copy(alpha = 0.28f),
            radius = radius * 0.22f,
            center = center + Offset(radius * 0.36f, radius * 0.28f)
        )
    }
}
