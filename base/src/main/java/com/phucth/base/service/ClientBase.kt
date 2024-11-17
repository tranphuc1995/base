package com.phucth.base.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientBase<T>(
    private val baseUrl: String,
    private val clientApi: Class<T>
) {


    public fun createClientApi(okHttpClient: OkHttpClient): T {
        val gson = initGson()
        val retrofit = initRetrofit(gson, okHttpClient)
        return retrofit.create(clientApi)
    }

    private fun initGson() = GsonBuilder().setLenient().create()
    private fun initRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
}