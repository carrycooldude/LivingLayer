package io.livinglayer.shader

class ShaderGraphBuilder {
    private val nodes = mutableListOf<ShaderNode>()
    private val edges = mutableListOf<ShaderEdge>()

    fun content(id: String = "content") = node(id, ShaderNodeType.Content)
    fun glass(id: String = "glass") = node(id, ShaderNodeType.Glass)
    fun hologram(id: String = "hologram") = node(id, ShaderNodeType.Hologram)
    fun liquid(id: String = "liquid") = node(id, ShaderNodeType.Liquid)
    fun neon(id: String = "neon") = node(id, ShaderNodeType.Neon)
    fun bloom(id: String = "bloom") = node(id, ShaderNodeType.Bloom)
    fun fresnel(id: String = "fresnel") = node(id, ShaderNodeType.Fresnel)
    fun output(id: String = "output") = node(id, ShaderNodeType.Output)

    fun connect(from: ShaderNode, to: ShaderNode, inputName: String = "content") = apply {
        edges += ShaderEdge(fromNodeId = from.id, toNodeId = to.id, inputName = inputName)
    }

    fun build(output: ShaderNode? = nodes.lastOrNull()): ShaderGraph {
        return ShaderGraph(
            nodes = nodes.toList(),
            edges = edges.toList(),
            outputNodeId = output?.id
        )
    }

    private fun node(id: String, type: ShaderNodeType): ShaderNode {
        val node = ShaderNode(id = id, type = type)
        nodes += node
        return node
    }
}

fun shaderGraph(block: ShaderGraphBuilder.() -> Unit): ShaderGraph {
    return ShaderGraphBuilder().apply(block).build()
}

