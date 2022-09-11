package net.gbappsolution.cryptocurrency.presentation.coin_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.gbappsolution.cryptocurrency.domain.use_case.get_coins.GetCoinUseCase
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.gbappsolution.cryptocurrency.common.Resource
import net.gbappsolution.cryptocurrency.domain.use_case.get_coin.GetCoinsUseCase


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase : GetCoinsUseCase
) : ViewModel(){
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occured!")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}