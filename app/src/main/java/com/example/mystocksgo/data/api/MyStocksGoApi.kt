package com.example.mystocksgo.data.api

import com.example.mystocksgo.data.api.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MyStocksGoApi {

    @GET("instrument/")
    suspend fun getInstruments(
        @Query("page", encoded = false) page: Int,
        @Query("limit", encoded = false) limit: Int,
        @Query("filter", encoded = false) filter: String,
        @Query("select", encoded = false) select: String,
        @Query("client_id", encoded = false) clientId: String,
        @Query("locale", encoded = false) locale: String
    ): ResponseDto
}