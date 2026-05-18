# LivingLayer

GPU-accelerated reactive Android UI layers with glass, holographic shaders, and sensor-aware depth.

LivingLayer is an Android-first graphics framework for building premium, motion-aware UI surfaces in Jetpack Compose. It is designed around a layer engine, an effect pipeline, AGSL shaders, and sensor input, so the same primitives can power icons, cards, controls, launcher surfaces, and future adaptive interfaces.

## Install

```kotlin
implementation("io.livinglayer:livinglayer:1.0.0-alpha01")
```

## Quick Start

```kotlin
LivingIcon(
    icon = painterResource(R.drawable.spotify)
)
```

## Advanced Use

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

## Fluent Layer API

```kotlin
val layer = LivingLayer {
    glass()
    glow()
    parallax()
    bloom()
    gyroscope()
}
```

## Architecture

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

LivingLayer treats everything as a layer. Layers render into a Compose draw scope, effects transform layers, shaders provide GPU-native material behavior, and motion engines make the result responsive to device tilt or touch.

## Modules

```text
io.livinglayer.core
io.livinglayer.compose
io.livinglayer.shader
io.livinglayer.sensor
io.livinglayer.effect
```

## Visual Direction

LivingLayer should feel futuristic, GPU-native, Android-first, holographic, and premium. The intended reference space is closer to Nothing OS, visionOS, PS5 UI, WinUI acrylic, and Qualcomm demo aesthetics than decorative Compose animation samples.

## Benchmarks

Target performance:

```text
Pixel 8 Pro -> 120 FPS
Snapdragon X Elite -> 144 FPS
```

Real benchmark numbers should be generated from the `benchmark` module before release.

## Roadmap

- V1: Compose API, layer engine, AGSL shader materials, sensor-aware depth.
- V2: Shader graph API.
- V3: Render backend abstraction with Vulkan extension point.
- V4: Android launcher surface engine.
- V5: Adaptive interface engine for future on-device AI workflows.

See `docs/versions.md` for the implemented package surfaces.
