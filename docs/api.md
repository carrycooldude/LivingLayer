# API Design

## Simple API

```kotlin
LivingIcon(
    icon = painterResource(R.drawable.spotify)
)
```

## Advanced API

```kotlin
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
val layer = LivingLayer {
    glass()
    glow()
    parallax()
    bloom()
    gyroscope()
}
```

The fluent API should stay expressive and memorable, but the runtime implementation should map to stable internal primitives: layers, effects, shaders, and motion engines.

