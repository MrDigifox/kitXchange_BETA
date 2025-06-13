package com.example.kitxchange
/** Matches the JSON returned by CoinGecko: {"monero":{"usd":325.12}} */
data class XmrResponse(
    val monero: Monero
)

data class Monero(
    val usd: Double
)
