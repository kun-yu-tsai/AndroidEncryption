package com.example.sdk.encryption.sha

import com.example.sdk.internal.toHexString
import java.security.MessageDigest

internal class Sha(private val algorithm : ShaAlgorithm) {

    fun hashing(byteArray: ByteArray): String {
        val msgD = MessageDigest.getInstance(algorithm.toString())
        val result = msgD.digest(byteArray)
        return result.toHexString()
    }

    fun hashing(string: String): String {
        return hashing(string.toByteArray())
    }
}