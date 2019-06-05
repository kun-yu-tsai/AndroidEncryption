package com.example.sdk.encryption

import com.example.sdk.internal.hexToByteArray
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class EncryptionTest {
    private lateinit var encryption : Encryption

    private val keyByteArray = "8C182623CD047A0D6593691B2179B98440A91AF01E4BB2BD90D49CC9E9D171E7".hexToByteArray()
    private val ivByteArray = "8DB023E39C39B95EBC0155DA9F14C37D".hexToByteArray()

    @Before
    fun before(){
        encryption = Encryption(
            EncryptionAlgorithm.AES,
            EncryptionAlgorithm.AES.getTransformation(),
            keyByteArray,
            ivByteArray
        )
    }

    @Test
    fun givenEncrypted_whenDecrypted_thenEqualResult() {
        val string = "string to be encrypted"
        val encrypted = encryption.encrypt(string.toByteArray())
        val decrypted = encryption.decrypt(encrypted)
        assertEquals(string, String(decrypted!!))
    }


    @Test
    fun givenSameInput_whenAESEncrypt_thenSameOutput() {
        val input = "device info".toByteArray()

        assertThat(
            encryption.encrypt(input),
            `is`(encryption.encrypt(input))
        )
    }

    @Test
    fun givenDifferentInput_whenAESEncrypt_thenDiffOutput() {
        assertThat(
            encryption.encrypt("device info".toByteArray()),
            not(encryption.encrypt("device info2".toByteArray()))
        )
    }

    @Test
    fun givenDifferentKey_whenAESEncrypt_thenDiffOutput() {
        val input = "device info".toByteArray()
        val encrypted = encryption.encrypt(input)
        encryption.key[0] = 0xAE.toByte()
        assertThat(
            encrypted,
            not(encryption.encrypt(input))
        )
    }

    @Test
    fun givenDifferentIv_whenAESEncrypt_thenDiffOutput() {
        val input = "device info".toByteArray()
        val encrypted = encryption.encrypt(input)
        encryption.iv[0] = 0xAE.toByte()
        assertThat(
            encrypted,
            not(encryption.encrypt(input))
        )
    }

}