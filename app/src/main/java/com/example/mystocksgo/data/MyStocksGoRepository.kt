package com.example.mystocksgo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.mystocksgo.data.api.MyStocksGoApi
import javax.inject.Inject
import javax.inject.Singleton

private const val DEFAULT_PAGE_SIZE = 20
private const val DEFAULT_MAXIMUM_SIZE = 100

@Singleton
class MyStocksGoRepository @Inject constructor(private val api: MyStocksGoApi) {

    fun getInstruments() =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                maxSize = DEFAULT_MAXIMUM_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { InstrumentPagingSource(api)}
        ).liveData
}