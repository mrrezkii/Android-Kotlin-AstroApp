package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.adapter.DataRSAdapter
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentLokasiBinding
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.network.response.DataRSResponse
import com.kedirilagi.astro.utils.showToast

class LokasiFragment : Fragment() {

    private lateinit var binding: FragmentLokasiBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private lateinit var adapter: DataRSAdapter
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetDialogTheme
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLokasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerView()
        setupObserver()
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Layanan Darurat")
        binding.btnNext.setOnClickListener {
            setupBottomSheet()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataRS()
    }

    private fun setupObserver() {
        observe(viewModel.dataRumahSakit) {
            adapter.setData(it)
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
    }

    private fun setupRecyclerView() {
        adapter = DataRSAdapter(arrayListOf(), object : DataRSAdapter.OnAdapterListener {
            override fun onClick(result: DataRSResponse) {
                findNavController().navigate(
                    R.id.action_lokasiFragment_to_sendMessageFragment,
                    bundleOf(
                        "nama_rs" to result.nama_rumah_sakit,
                        "alamat" to result.alamat,
                        "nomor" to result.nomer_telepon
                    )
                )
                bottomSheetDialog.dismiss()
            }
        })
    }

    private fun setupBottomSheet() {
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottomsheet_lokasi,
            requireActivity().findViewById(R.id.lokasi_bottomsheet)
        )

        val list = bottomSheetView.findViewById<RecyclerView>(R.id.list_lokasi)
        val searchView = bottomSheetView.findViewById<EditText>(R.id.searchView)
        searchView.keyListener = null
        searchView.setOnClickListener {
            findNavController().navigate(R.id.action_lokasiFragment_to_detailLokasiFragment)
            bottomSheetDialog.dismiss()
        }

        list.adapter = adapter

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

}