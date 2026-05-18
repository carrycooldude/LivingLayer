# LivingLayer

GPU-accelerated reactive Android UI layers with glass, holographic shaders, and sensor-aware depth.

LivingLayer is an Android-first graphics framework for building premium, motion-aware UI surfaces in Jetpack Compose. It is designed around a layer engine, an effect pipeline, AGSL shaders, and sensor input, so the same primitives can power icons, cards, controls, launcher surfaces, and future adaptive interfaces.

## Maven Package Description

LivingLayer is a GPU-native reactive Android UI layer framework for Jetpack Compose. It provides holographic shaders, glass materials, bloom/glow effects, sensor-aware depth, shader graph primitives, and adaptive surface APIs for building premium Android interfaces.

## Install

```kotlin
implementation("io.livinglayer:livinglayer:1.0.0-alpha01")
```

GitHub Packages feed:

```text
https://maven.pkg.github.com/carrycooldude/LivingLayer
```

## Quick Start

```kotlin
import io.livinglayer.compose.LivingIcon

LivingIcon(
    icon = painterResource(R.drawable.spotify)
)
```

## Advanced Use

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

Use this when you want a single icon or visual object to feel dimensional, reflective, and alive.

## Fluent Layer API

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

Use this when you want to describe a reusable graphics surface and pass it into renderers, launcher surfaces, or adaptive interfaces.

## Examples

### 1. Glass App Icon

```kotlin
@Composable
fun GlassAppIcon() {
    LivingIcon(
        icon = painterResource(R.drawable.ic_music),
        config = LivingLayerConfig(
            depth = 18f,
            glow = true,
            bloom = false,
            reflections = true,
            gyroscope = true
        )
    )
}
```

### 2. Neon/Holographic Icon

```kotlin
@Composable
fun NeonIcon() {
    LivingIcon(
        icon = painterResource(R.drawable.ic_wallet),
        config = LivingLayerConfig(
            depth = 30f,
            glow = true,
            bloom = true,
            reflections = true,
            shader = LivingShaderPreset.Neon
        )
    )
}
```

### 3. Shader Graph Surface

```kotlin
import io.livinglayer.shader.shaderGraph

val holographicGraph = shaderGraph {
    val content = content()
    val glass = glass()
    val hologram = hologram()
    val output = output()

    connect(content, glass)
    connect(glass, hologram)
    connect(hologram, output)
}

val layer = LivingLayer {
    glass()
    glow()
    fresnel()
    shaderGraph(holographicGraph)
}
```

### 4. Launcher-Style Surface Grid

```kotlin
import io.livinglayer.launcher.LauncherEngine
import io.livinglayer.launcher.LauncherItem
import io.livinglayer.launcher.LauncherViewport

val baseLayer = LivingLayer {
    glass()
    glow()
    parallax()
}

val scene = LauncherEngine().resolveLayout(
    items = listOf(
        LauncherItem("music", "Music", baseLayer),
        LauncherItem("camera", "Camera", baseLayer),
        LauncherItem("wallet", "Wallet", baseLayer)
    ),
    viewport = LauncherViewport(widthDp = 420, heightDp = 860)
)
```

### 5. Adaptive Interface

```kotlin
import io.livinglayer.adaptive.AdaptiveInterfaceEngine
import io.livinglayer.adaptive.AdaptiveInterfaceRequest
import io.livinglayer.adaptive.InterfaceContext

val adaptive = AdaptiveInterfaceEngine().generate(
    AdaptiveInterfaceRequest(
        id = "focus-card",
        context = InterfaceContext.Focused,
        motionAware = true,
        holographic = true
    )
)

val surface = adaptive.surface
```

This gives you a generated `LivingSurface` with depth, glow, bloom, reflections, and motion settings chosen from the requested interface context.

## Test The Package

Publish locally:

```powershell
.\gradlew.bat publishToMavenLocal
```

Run API tests and build the sample app:

```powershell
.\gradlew.bat :livinglayer:testReleaseUnitTest :sample:assembleDebug
```

Install the sample on a connected Android device or emulator:

```powershell
.\gradlew.bat :sample:installDebug
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
