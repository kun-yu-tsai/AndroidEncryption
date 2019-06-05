package com.example.sdk.encryption

internal enum class EncryptionAlgorithm {
    AES;

    fun getTransformation() : String{
        return "$this/CBC/PKCS5PADDING"
    }
}