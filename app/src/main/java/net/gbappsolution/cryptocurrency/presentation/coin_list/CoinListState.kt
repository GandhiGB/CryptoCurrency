package net.gbappsolution.cryptocurrency.presentation.coin_list

import net.gbappsolution.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
