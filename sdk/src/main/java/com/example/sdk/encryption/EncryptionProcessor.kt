package com.example.sdk.encryption

import com.example.sdk.encryption.sha.Sha

internal class EncryptionProcessor(private val encryption: Encryption, private val sha: Sha) {
    fun process(stringToEncrypt: String) : String{
        val encryptedData = encryption.encrypt(stringToEncrypt.toByteArray())
        return sha.hashing(encryptedData)
    }
}
