package io.livinglayer.core

import io.livinglayer.effect.BloomEffect
import io.livinglayer.effect.BlurEffect
import io.livinglayer.effect.Effect
import io.livinglayer.effect.FresnelEffect
import io.livinglayer.effect.RefractionEffect
import io.livinglayer.effect.RippleEffect
import io.livinglayer.sensor.MotionEngine
import io.livinglayer.shader.ShaderGraph

class LivingLayerSpec internal constructor(
    val effects: List<Effect>,
    val motionEngine: MotionEngine?,
    val shaderGraph: ShaderGraph?
)

class LivingLayerBuilder {
    private val effects = mutableListOf<Effect>()
    private var motionEngine: MotionEngine? = null
    private var shaderGraph: ShaderGraph? = null

    fun glass() = apply {
        effects += BlurEffect(radius = 18f)
        effects += RefractionEffect(strength = 0.12f)
    }

    fun glow() = apply {
        effects += BloomEffect(intensity = 0.72f)
    }

    fun bloom() = apply {
        effects += BloomEffect(intensity = 1f)
    }

    fun ripple() = apply {
        effects += RippleEffect(amplitude = 0.16f)
    }

    fun fresnel() = apply {
        effects += FresnelEffect(power = 2.2f)
    }

    fun shaderGraph(graph: ShaderGraph) = apply {
        shaderGraph = graph
    }

    fun parallax() = apply {
        // Parallax is resolved by renderers that read the configured motion engine.
    }

    fun gyroscope(engine: MotionEngine? = null) = apply {
        motionEngine = engine
    }

    internal fun build(): LivingLayerSpec {
        return LivingLayerSpec(
            effects = effects.toList(),
            motionEngine = motionEngine,
            shaderGraph = shaderGraph
        )
    }
}

fun LivingLayer(block: LivingLayerBuilder.() -> Unit): LivingLayerSpec {
    return LivingLayerBuilder().apply(block).build()
}
