package net.gbappsolution.cryptocurrency.presentation.coin_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.gbappsolution.cryptocurrency.common.Constants
import net.gbappsolution.cryptocurrency.common.Resource
import net.gbappsolution.cryptocurrency.domain.use_case.get_coins.GetCoinUseCase


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase : GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coins = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "An unexpected error occured!")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}