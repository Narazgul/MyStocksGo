package com.example.mystocksgo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InstrumentAdapter : RecyclerView.Adapter<InstrumentViewHolder>() {

    private val data = listOf<Instrument>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.instrument_item, parent, false)
        return InstrumentViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class InstrumentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val instrument: TextView = view.findViewById(R.id.instrument)
    private val exchange: TextView = view.findViewById(R.id.exchange)
    private val quote: TextView = view.findViewById(R.id.quote)

    fun bind(data: Instrument) {
        also {
            instrument.text = data.instrument
            exchange.text = data.exchange
            quote.text = data.quote.toString()
        }
    }
}