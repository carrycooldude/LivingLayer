# LivingLayer Architecture

LivingLayer is organized as a rendering stack rather than a single visual effect.

```text
Compose API
    |
Layer Engine
    |
Effect Pipeline
    |
Shader System
    |
Sensor Engine
```

## Compose API

The Compose API is the product surface. It should stay simple for first-use adoption while exposing enough configuration for deeper graphics work.

```kotlin
LivingIcon(
    icon = painterResource(R.drawable.spotify)
)
```

## Layer Engine

Everything is a layer. A layer owns rendering behavior and can be combined with other layers to build a richer surface.

Examples:

- GlassLayer
- GlowLayer
- MeshLayer
- ReflectionLayer
- ParticleLayer

## Effect Pipeline

Effects operate on layers and should remain composable. Effects can later target RenderEffect, RuntimeShader, or a lower-level backend without forcing API changes.

Examples:

- BloomEffect
- BlurEffect
- RefractionEffect
- FresnelEffect
- RippleEffect

## Shader System

The shader system manages AGSL sources and RuntimeShader creation on Android 13 and newer.

Initial bundled shaders:

- glass.agsl
- hologram.agsl
- liquid.agsl
- neon.agsl

## Sensor Engine

The sensor engine abstracts motion input so device gyroscope, touch, and fake/test motion all share one contract.

```kotlin
interface MotionEngine {
    val rotationX: Float
    val rotationY: Float
}
```

