package org.chosun.dodamduck.model.network

import org.chosun.dodamduck.model.dto.Trade
import retrofit2.http.GET

interface TradeApiService {
    @GET("Post.php")
    suspend fun getTradeList(): List<Trade>?
}