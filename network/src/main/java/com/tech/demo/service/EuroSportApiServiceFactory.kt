package com.tech.demo.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object EuroSportApiServiceFactory {

    fun provideService(): EuroSportApiService {
        val okHttpClient = makeOkHttpClient()
        return provideService(okHttpClient, Gson())
    }


    private fun provideService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): EuroSportApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://extendsclass.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(EuroSportApiService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}