package com.phucth.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClientBase<T>(
    private val baseUrl: String,
    private val clientApi: Class<T>
) {


    public fun createClientApi(): T {
        val gson = initGson()
        val retrofit = initRetrofit(gson)
        return retrofit.create(clientApi)
    }

    private fun initGson() = GsonBuilder().setLenient().create()
    private fun initRetrofit(gson: Gson) =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}