package com.example.sdk.internal

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class ExtensionsKtTest {
    @Test
    fun hexToByteArray() {
        assertThat("A43B".hexToByteArray(), `is`(byteArrayOf(0xA4.toByte(), 0x3B.toByte())))
        assertThat(
            "14323A5B".hexToByteArray(),
            `is`(
                byteArrayOf(
                    0x14.toByte(),
                    0x32.toByte(),
                    0x3A.toByte(),
                    0x5B.toByte()
                )
            )
        )
    }

    @Test
    fun byteArrayToHex(){
        assertThat(byteArrayOf(0xA4.toByte(), 0x3B.toByte()).toHexString(), `is`("a43b"))
        assertThat(byteArrayOf(
            0x14.toByte(),
            0x32.toByte(),
            0x3A.toByte(),
            0x5B.toByte()
        ).toHexString(), `is`("14323a5b"))
    }
}