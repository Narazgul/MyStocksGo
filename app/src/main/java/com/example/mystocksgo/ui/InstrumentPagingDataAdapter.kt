package com.example.mystocksgo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mystocksgo.databinding.InstrumentItemBinding
import com.example.mystocksgo.domain.Instrument

class InstrumentPagingDataAdapter :
    PagingDataAdapter<Instrument,
            InstrumentPagingDataAdapter.InstrumentViewHolder>(INSTRUMENT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentViewHolder {
        val binding = InstrumentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstrumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(currentItem) }
    }

    companion object {
        private val INSTRUMENT_COMPARATOR = object : DiffUtil.ItemCallback<Instrument>() {
            override fun areItemsTheSame(oldItem: Instrument, newItem: Instrument) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Instrument, newItem: Instrument) =
                oldItem == newItem
        }
    }

    class InstrumentViewHolder(private val binding: InstrumentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentInstrument: Instrument) {
            binding.apply {
                instrument.text = currentInstrument.name
                exchange.text = currentInstrument.exchangeName
                quote.text = currentInstrument.quoteWithUnitString
            }
        }
    }
}