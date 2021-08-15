package com.example.mystocksgo.data.api.dto

import java.sql.Timestamp

data class QuoteDto(
    val value: Float?,
    val time: Timestamp?,
    val changePerc: Float?,
    val prevClose: Float?
)