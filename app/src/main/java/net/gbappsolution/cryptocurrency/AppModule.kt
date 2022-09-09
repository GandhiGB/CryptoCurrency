package net.gbappsolution.cryptocurrency

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.gbappsolution.cryptocurrency.common.Constants
import net.gbappsolution.cryptocurrency.data.remote.CoinPaprikaAPI
import net.gbappsolution.cryptocurrency.data.repository.CoinRepository
import net.gbappsolution.cryptocurrency.domain.contract.ICoinRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaAPI(): CoinPaprikaAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaAPI): ICoinRepository {
        return CoinRepository(api)
    }
}