package com.example.sdk

import android.content.Context
import com.example.sdk.data.Info
import com.example.sdk.data.device.DeviceInfo
import com.example.sdk.data.network.ApiService
import com.example.sdk.encryption.Encryption
import com.example.sdk.encryption.EncryptionAlgorithm
import com.example.sdk.encryption.EncryptionProcessor
import com.example.sdk.encryption.sha.Sha
import com.example.sdk.encryption.sha.ShaAlgorithm
import com.example.sdk.internal.hexToByteArray
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataHandlingSDK(
    context: Context
) {
    private val context = context.applicationContext
    private val gson = Gson()

    private val keyByteArray = "AD13469AC91B7ED9ECC48BA281057EC3D125489A3CD098156B7D8A35C100ACE3".hexToByteArray()
    private val ivByteArray = "3A28DE9A0C745DDE13CA78EB9C7803DA".hexToByteArray()

    fun getDeviceHashCode(): String {

        val encryptionProcessor = EncryptionProcessor(
            Encryption(
                EncryptionAlgorithm.AES,
                EncryptionAlgorithm.AES.getTransformation(),
                keyByteArray,
                ivByteArray
            ),
            Sha(ShaAlgorithm.SHA1)
        )

        val deviceInfo = DeviceInfo().getDeviceInfo(context)
        val deviceInfoJson = gson.toJson(deviceInfo)

        return encryptionProcessor.process(deviceInfoJson)
    }

    fun updateInfo(onSuccess: () -> Unit, onFailed: () -> Unit) {
        val customerInfo = Info(
            DeviceInfo().getDeviceInfo(context),
            System.currentTimeMillis()
        )
        customerInfo.device.custom = Info.Device.Custom(getDeviceHashCode())

        val apiService = ApiService()
        apiService.updateDevice(customerInfo).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                onFailed()
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                onSuccess()
            }
        })
    }
}