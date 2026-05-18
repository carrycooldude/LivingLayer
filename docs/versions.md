# Versioned Roadmap Implementation

## V1: Reactive Layers

Implemented in the base package:

- `LivingIcon`
- `LivingLayerConfig`
- `Layer`
- `LivingRenderer`
- Effects: bloom, blur, ripple, refraction, fresnel, reflection
- Motion engines: gyroscope, touch, fake
- Bundled AGSL shader sources

## V2: Shader Graph

Implemented as a public graph model:

- `ShaderGraph`
- `ShaderNode`
- `ShaderEdge`
- `ShaderGraphBuilder`
- `shaderGraph { }`

This gives the package a stable API before adding graph compilation and shader optimization.

## V3: Vulkan Backend

Implemented as a backend abstraction:

- `RenderBackend`
- `ComposeRenderBackend`
- `VulkanBackend.unavailable()`

The base package exposes the extension point without bundling native Vulkan binaries.

## V4: Android Launcher Engine

Implemented as a layout and surface engine:

- `LauncherEngine`
- `LauncherItem`
- `LauncherScene`
- `LauncherCell`
- `LauncherViewport`

This is the start of a launcher-grade UI surface system rather than a single icon effect.

## V5: Adaptive Interfaces

Implemented as deterministic adaptive generation:

- `AdaptiveInterfaceEngine`
- `AdaptivePolicy`
- `AdaptiveInterfaceRequest`
- `AdaptiveInterface`

The current implementation is local and predictable. Future versions can connect this to on-device AI models.

