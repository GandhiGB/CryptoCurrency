package net.gbappsolution.cryptocurrency.data.repository

import net.gbappsolution.cryptocurrency.data.remote.CoinPaprikaAPI
import net.gbappsolution.cryptocurrency.data.remote.dto.CoinDTO
import net.gbappsolution.cryptocurrency.data.remote.dto.CoinDetailDTO
import net.gbappsolution.cryptocurrency.domain.contract.ICoinRepository
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinPaprikaAPI
) : ICoinRepository{
    override suspend fun getCoins(): List<CoinDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getContById(): CoinDetailDTO {
        TODO("Not yet implemented")
    }
}