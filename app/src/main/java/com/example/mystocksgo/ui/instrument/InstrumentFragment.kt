package com.example.mystocksgo.ui.instrument

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystocksgo.R
import com.example.mystocksgo.databinding.FragmentInstrumentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstrumentFragment : Fragment(R.layout.fragment_instrument) {

    private val viewModel by viewModels<InstrumentViewModel>()
    private var _binding : FragmentInstrumentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentInstrumentBinding.bind(view)

        val adapter = InstrumentPagingDataAdapter()
        setupRecyclerView(adapter)
        startObservingInstrumentsUpdates(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(adapter: InstrumentPagingDataAdapter) {
        binding.apply {
            recyclerview.setHasFixedSize(true)
            val layoutManager = recyclerview.layoutManager as LinearLayoutManager
            val dividerItemDecoration = DividerItemDecoration(
                recyclerview.context,
                layoutManager.orientation)
            recyclerview.addItemDecoration(dividerItemDecoration)
            recyclerview.adapter = adapter
        }
    }

    private fun startObservingInstrumentsUpdates(adapter: InstrumentPagingDataAdapter) {
        viewModel.instruments.observe(viewLifecycleOwner) { instruments ->
            adapter.submitData(viewLifecycleOwner.lifecycle, instruments)
        }
    }
}