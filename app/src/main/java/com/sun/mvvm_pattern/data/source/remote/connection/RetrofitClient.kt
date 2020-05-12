package com.sun.mvvm_pattern.data.source.remote.connection

import com.sun.mvvm_pattern.data.source.remote.api.UserService
import com.sun.mvvm_pattern.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private var userService: UserService? = null

    private object HOLDER {
        val INSTANCE = RetrofitClient()
    }

    fun getUserService(): UserService? {
        if (userService == null) {
            userService = provideRetrofit().create(UserService::class.java)
        }
        return userService
    }

    private fun provideRetrofit(): Retrofit {
        val logging =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        const val READ_TIMEOUT = 5000L
        const val WRITE_TIMEOUT = 5000L
        const val CONNECT_TIMEOUT = 5000L
        val instance: RetrofitClient by lazy { HOLDER.INSTANCE }
    }
}