package com.example.sdk.encryption

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

internal class Encryption(
    var algorithm: EncryptionAlgorithm,
    var transformation: String,
    var key: ByteArray,
    var iv: ByteArray
) {

    fun encrypt(
        byteArrayToEncrypt: ByteArray
    ): ByteArray{
        return cipherProcess(Cipher.ENCRYPT_MODE, byteArrayToEncrypt)
    }

    fun decrypt(
        encryptedByteArray: ByteArray
    ): ByteArray? {
        return cipherProcess(Cipher.DECRYPT_MODE, encryptedByteArray)
    }

    private fun cipherProcess(
        cipherMode: Int,
        data: ByteArray
    ): ByteArray {
        val keySpec = SecretKeySpec(key, algorithm.name)
        val ivSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance(transformation)
        cipher.init(cipherMode, keySpec, ivSpec)
        return cipher.doFinal(data)
    }
}