package io.livinglayer.shader

import android.graphics.RuntimeShader

class ShaderManager(
    private val shaders: Map<String, String> = emptyMap()
) {
    fun load(name: String): RuntimeShader {
        val source = shaders[name] ?: error("Shader '$name' is not registered.")
        return RuntimeShader(source)
    }
}
