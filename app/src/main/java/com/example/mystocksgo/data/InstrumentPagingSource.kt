package com.example.mystocksgo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mystocksgo.data.api.MyStocksGoApi
import com.example.mystocksgo.data.api.dto.InstrumentDto
import com.example.mystocksgo.domain.Instrument
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
private const val DEFAULT_LOAD_SIZE = 20
private const val DEFAULT_FILTER = "country.id=23 AND assetClass.id=2"
private const val DEFAULT_SELECTION = "name,autoQuotation[exchange[name],unit[name],quote[value]]"
private const val CLIENT_ID = "grid"
private const val DEFAULT_LOCALE = "en"

class InstrumentPagingSource(
    private val api: MyStocksGoApi,
    private val filter: String = DEFAULT_FILTER,
    private val selection: String = DEFAULT_SELECTION,
    private val locale: String = DEFAULT_LOCALE
) : PagingSource<Int, Instrument>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Instrument> {
        val position = params.key ?: STARTING_PAGE_INDEX

        try {
            val response = api.getInstruments(
                page = position,
                limit = DEFAULT_LOAD_SIZE,
                filter = filter,
                select = selection,
                clientId = CLIENT_ID,
                locale = locale
            )

            val instrumentsDto = response.data
            val instruments = instrumentsDto.mapToInstrumentsDomainModel()

            return LoadResult.Page(
                data = instruments,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (instrumentsDto.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Instrument>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    private fun List<InstrumentDto>.mapToInstrumentsDomainModel(): List<Instrument> {
        return this.map {

            val quoteValueString = getQuoteValueOrEmptyString(it)

            Instrument(
                id = it.id ?: -1,
                name = it.name,
                exchangeName = it.autoQuotation?.exchange?.name ?: "",
                quoteValue = quoteValueString,
                unitName = it.autoQuotation?.unit?.name ?: ""
            )
        }
    }

    private fun getQuoteValueOrEmptyString(instrumentDto: InstrumentDto): String {
        val autoQuotation = instrumentDto.autoQuotation ?: return ""
        val quote = autoQuotation.quote ?: return ""
        val value = quote.value ?: ""
        return value.toString()
    }
}