package io.livinglayer.shader

data class ShaderGraph(
    val nodes: List<ShaderNode>,
    val edges: List<ShaderEdge> = emptyList(),
    val outputNodeId: String? = nodes.lastOrNull()?.id
) {
    init {
        require(nodes.map { it.id }.toSet().size == nodes.size) {
            "Shader node ids must be unique."
        }
    }
}

data class ShaderNode(
    val id: String,
    val type: ShaderNodeType,
    val uniforms: Map<String, ShaderUniform> = emptyMap()
)

data class ShaderEdge(
    val fromNodeId: String,
    val toNodeId: String,
    val inputName: String
)

enum class ShaderNodeType {
    Content,
    Glass,
    Hologram,
    Liquid,
    Neon,
    Bloom,
    Fresnel,
    Output
}

sealed interface ShaderUniform {
    data class FloatValue(val value: Float) : ShaderUniform
    data class Float2Value(val x: Float, val y: Float) : ShaderUniform
    data class ColorValue(val red: Float, val green: Float, val blue: Float, val alpha: Float = 1f) : ShaderUniform
}

