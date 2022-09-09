package net.gbappsolution.cryptocurrency.data.remote

import net.gbappsolution.cryptocurrency.data.remote.dto.CoinDTO
import net.gbappsolution.cryptocurrency.data.remote.dto.CoinDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaAPI {
    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDTO>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String) : CoinDetailDTO
}