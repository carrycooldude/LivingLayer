package io.livinglayer.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class GyroscopeMotionEngine(
    context: Context
) : MotionEngine, SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    override var rotationX: Float = 0f
        private set

    override var rotationY: Float = 0f
        private set

    fun start() {
        gyroscope?.let { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        rotationX = event.values.getOrNull(0) ?: 0f
        rotationY = event.values.getOrNull(1) ?: 0f
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}

