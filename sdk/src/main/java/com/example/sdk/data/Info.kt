package com.example.sdk.data


internal data class Info(
    val device: Device,
    val timestamp: Long
) {
    data class Device(
        val deviceId: String,
        val manufacturer: String,
        val model: String,
        val os: String,
        val type: String,
        var custom: Custom? = null
    ) {
        data class Custom(
            val deviceInfo: String
        )
    }
}