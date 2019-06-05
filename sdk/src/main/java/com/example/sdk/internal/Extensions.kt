package com.example.sdk.internal

internal fun String.hexToByteArray() : ByteArray{
    val len = this.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(this[i], 16) shl 4) + Character.digit(this[i + 1], 16)).toByte()
        i += 2
    }
    return data
}

internal fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }