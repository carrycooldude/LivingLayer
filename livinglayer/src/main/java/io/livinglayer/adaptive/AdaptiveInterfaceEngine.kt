package io.livinglayer.adaptive

import io.livinglayer.core.LivingLayer
import io.livinglayer.core.LivingLayerConfig
import io.livinglayer.core.LivingSurface

class AdaptiveInterfaceEngine(
    private val policy: AdaptivePolicy = AdaptivePolicy()
) {
    fun generate(request: AdaptiveInterfaceRequest): AdaptiveInterface {
        val depth = when (request.context) {
            InterfaceContext.Ambient -> policy.ambientDepth
            InterfaceContext.Focused -> policy.focusedDepth
            InterfaceContext.Performance -> policy.performanceDepth
        }

        val layer = LivingLayer {
            glass()
            glow()
            if (request.holographic) fresnel()
            if (request.motionAware) parallax()
        }

        return AdaptiveInterface(
            surface = LivingSurface(
                id = request.id,
                layer = layer,
                config = LivingLayerConfig(
                    depth = depth,
                    glow = request.holographic,
                    bloom = request.context != InterfaceContext.Performance,
                    reflections = request.context != InterfaceContext.Performance,
                    gyroscope = request.motionAware,
                    adaptive = true
                )
            ),
            rationale = "Generated from context=${request.context}, motionAware=${request.motionAware}, holographic=${request.holographic}."
        )
    }
}

data class AdaptivePolicy(
    val ambientDepth: Float = 12f,
    val focusedDepth: Float = 28f,
    val performanceDepth: Float = 8f
)

data class AdaptiveInterfaceRequest(
    val id: String,
    val context: InterfaceContext,
    val motionAware: Boolean = true,
    val holographic: Boolean = true
)

data class AdaptiveInterface(
    val surface: LivingSurface,
    val rationale: String
)

enum class InterfaceContext {
    Ambient,
    Focused,
    Performance
}

