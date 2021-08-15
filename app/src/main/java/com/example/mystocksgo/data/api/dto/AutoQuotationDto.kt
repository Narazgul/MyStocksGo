package com.example.mystocksgo.data.api.dto

data class AutoQuotationDto(
    val exchange: ExchangeDto?,
    val unit: UnitDto?,
    val quote: QuoteDto?
)