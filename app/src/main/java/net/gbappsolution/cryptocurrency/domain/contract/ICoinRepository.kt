package net.gbappsolution.cryptocurrency.domain.contract

import net.gbappsolution.cryptocurrency.data.remote.dto.CoinDTO
import net.gbappsolution.cryptocurrency.data.remote.dto.CoinDetailDTO

interface ICoinRepository {
    suspend fun getCoins(): List<CoinDTO>
    suspend fun getContById(): CoinDetailDTO
}