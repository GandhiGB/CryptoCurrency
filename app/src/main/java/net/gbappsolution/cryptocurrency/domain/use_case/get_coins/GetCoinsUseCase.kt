package net.gbappsolution.cryptocurrency.domain.use_case.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.gbappsolution.cryptocurrency.common.Resource
import net.gbappsolution.cryptocurrency.data.remote.dto.toCoin
import net.gbappsolution.cryptocurrency.domain.contract.ICoinRepository
import net.gbappsolution.cryptocurrency.domain.model.Coin
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository : ICoinRepository
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }
}