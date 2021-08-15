package com.example.mystocksgo.ui.instrument

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mystocksgo.data.MyStocksGoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InstrumentViewModel @Inject constructor(repository: MyStocksGoRepository) :
    ViewModel() {

    val instruments = repository.getInstruments().cachedIn(viewModelScope)
}