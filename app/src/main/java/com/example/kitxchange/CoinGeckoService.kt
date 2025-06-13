package com.example.kitxchange

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.kitxchange.XmrResponse

/**API for CoinGecko’s simple price endpoint */
interface CoinGeckoService {

    @GET("simple/price")
    suspend fun getXmrPrice(
        @Query("ids") ids: String = "monero",
        @Query("vs_currencies") vs: String = "usd"
    ): XmrResponse
}
