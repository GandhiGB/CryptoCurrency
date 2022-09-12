package net.gbappsolution.cryptocurrency.domain.use_case.get_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gbappsolution.cryptocurrency.common.Resource
import net.gbappsolution.cryptocurrency.data.remote.dto.toCoinDetail
import net.gbappsolution.cryptocurrency.domain.contract.ICoinRepository
import net.gbappsolution.cryptocurrency.domain.model.CoinDetail
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository : ICoinRepository
){
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch(e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}