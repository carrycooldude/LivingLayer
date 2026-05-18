package io.livinglayer.sensor

class TouchMotionEngine : MotionEngine {
    override var rotationX: Float = 0f
        private set

    override var rotationY: Float = 0f
        private set

    fun update(rotationX: Float, rotationY: Float) {
        this.rotationX = rotationX
        this.rotationY = rotationY
    }
}

