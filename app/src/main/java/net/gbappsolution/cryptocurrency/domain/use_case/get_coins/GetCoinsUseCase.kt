package net.gbappsolution.cryptocurrency.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gbappsolution.cryptocurrency.common.Resource
import net.gbappsolution.cryptocurrency.data.remote.dto.toCoin
import net.gbappsolution.cryptocurrency.data.remote.dto.toCoinDetail
import net.gbappsolution.cryptocurrency.domain.contract.ICoinRepository
import net.gbappsolution.cryptocurrency.domain.model.Coin
import net.gbappsolution.cryptocurrency.domain.model.CoinDetail
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository : ICoinRepository
){
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coins))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured!"))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "Could not reach server. Please check your connection!"))
        }
    }
}