package com.example.mystocksgo.data.api.dto

data class QuotationsDto(
    val exchange: ExchangeDto?,
    val unit: UnitDto?,
    val quote: QuoteDto?
)