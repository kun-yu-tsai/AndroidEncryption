package com.example.sdk.encryption.sha

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class ShaTest {
    private lateinit var sha1: Sha

    @Before
    fun before() {
        sha1 = Sha(ShaAlgorithm.SHA1)
    }

    @Test
    fun givenSameInput_whenSha1Hashing_thenSameOutput() {
        assertThat(sha1.hashing("9A9B"), `is`(sha1.hashing("9A9B")))
    }

    @Test
    fun givenOneCharDifferent_whenSha1Hashing_thenDifferentOutput() {
        assertThat(sha1.hashing("9A9B"), not(sha1.hashing("9A9b")))
    }
}