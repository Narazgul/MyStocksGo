package com.example.mystocksgo.data.api.dto

data class InstrumentDto(
    val id: Int?,
    val name: String = "",
    val quotations: QuotationsDto?,
    val autoQuotation: AutoQuotationDto?
)