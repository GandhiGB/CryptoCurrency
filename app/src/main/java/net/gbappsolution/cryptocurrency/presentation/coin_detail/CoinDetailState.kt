package net.gbappsolution.cryptocurrency.presentation.coin_detail

import net.gbappsolution.cryptocurrency.domain.model.Coin
import net.gbappsolution.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = ""
)
