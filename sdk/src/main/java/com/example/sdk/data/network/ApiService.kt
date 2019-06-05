package com.example.sdk.data.network

import com.example.sdk.data.Info
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

internal interface ApiService {

    @POST("device")
    fun updateDevice(@Body info: Info): Call<Any>

    companion object {
        operator fun invoke(): ApiService {
            val addHeaderInterceptor = Interceptor { chain ->
                val addedHeaderRequest = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()

                chain.proceed(addedHeaderRequest)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(addHeaderInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://kun-web-app.com.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}