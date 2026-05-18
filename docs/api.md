# API Design

## Simple API

```kotlin
import io.livinglayer.compose.LivingIcon

LivingIcon(
    icon = painterResource(R.drawable.spotify)
)
```

## Advanced API

```kotlin
import io.livinglayer.compose.LivingIcon
import io.livinglayer.core.LivingLayerConfig

LivingIcon(
    icon = painter,
    config = LivingLayerConfig(
        depth = 24f,
        glow = true,
        bloom = true,
        reflections = true,
        gyroscope = true
    )
)
```

## Fluent Builder API

```kotlin
import io.livinglayer.core.LivingLayer

val layer = LivingLayer {
    glass()
    glow()
    parallax()
    bloom()
    gyroscope()
}
```

The fluent API should stay expressive and memorable, but the runtime implementation should map to stable internal primitives: layers, effects, shaders, and motion engines.

## Shader Graph Example

```kotlin
import io.livinglayer.core.LivingLayer
import io.livinglayer.shader.shaderGraph

val graph = shaderGraph {
    val content = content()
    val glass = glass()
    val neon = neon()
    val output = output()

    connect(content, glass)
    connect(glass, neon)
    connect(neon, output)
}

val layer = LivingLayer {
    glass()
    glow()
    shaderGraph(graph)
}
```

## Launcher Scene Example

```kotlin
import io.livinglayer.core.LivingLayer
import io.livinglayer.launcher.LauncherEngine
import io.livinglayer.launcher.LauncherItem
import io.livinglayer.launcher.LauncherViewport

val appLayer = LivingLayer {
    glass()
    glow()
    parallax()
}

val scene = LauncherEngine().resolveLayout(
    items = listOf(
        LauncherItem(id = "music", label = "Music", layer = appLayer),
        LauncherItem(id = "camera", label = "Camera", layer = appLayer)
    ),
    viewport = LauncherViewport(widthDp = 420, heightDp = 860)
)
```

## Adaptive Interface Example

```kotlin
import io.livinglayer.adaptive.AdaptiveInterfaceEngine
import io.livinglayer.adaptive.AdaptiveInterfaceRequest
import io.livinglayer.adaptive.InterfaceContext

val generated = AdaptiveInterfaceEngine().generate(
    AdaptiveInterfaceRequest(
        id = "control-surface",
        context = InterfaceContext.Focused,
        motionAware = true,
        holographic = true
    )
)

val surface = generated.surface
```
