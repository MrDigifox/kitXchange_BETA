package com.example.kitxchange

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Net {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CoinGeckoService by lazy {
        retrofit.create(CoinGeckoService::class.java)
    }
}
