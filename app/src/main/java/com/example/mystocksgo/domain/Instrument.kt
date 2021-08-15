package com.example.mystocksgo.domain

data class Instrument(
    val id: Int,
    val name: String,
    val exchangeName: String,
    val quoteValue: String,
    val unitName: String
) {
    val quoteWithUnitString = "$quoteValue $unitName"
}