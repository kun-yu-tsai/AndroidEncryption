package com.example.encryptionandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sdk.DataHandlingSDK
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var dataHandlingSDK: DataHandlingSDK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataHandlingSDK = DataHandlingSDK(
            this
        )

        get_device_info_button.setOnClickListener {
            device_info_hash_text.text = dataHandlingSDK.getDeviceHashCode()
        }

        post_request_button.setOnClickListener {
            dataHandlingSDK.updateInfo(
                { request_result_text.text = "Success" },
                { request_result_text.text = "Failed" }
            )
        }
    }
}