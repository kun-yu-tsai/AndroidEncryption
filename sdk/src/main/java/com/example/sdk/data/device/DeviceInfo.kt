package com.example.sdk.data.device

import android.content.Context
import android.os.Build
import android.provider.Settings
import com.example.sdk.data.Info

internal class DeviceInfo {
    fun getDeviceInfo(context: Context): Info.Device {
        val android_id = Settings.Secure.getString(
            context.getContentResolver(),
            Settings.Secure.ANDROID_ID
        )

        return Info.Device(
            android_id,
            Build.MANUFACTURER,
            Build.MODEL,
            "android",
            "phone"
        )
    }

}