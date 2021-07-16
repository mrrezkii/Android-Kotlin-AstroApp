package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.view.adapter.DataRSAdapter
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentDetailLokasiBinding

class DetailLokasiFragment : Fragment() {

    private lateinit var binding: FragmentDetailLokasiBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private lateinit var adapter: DataRSAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailLokasiBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupView()
//        setupRecyclerView()
//        setupObserver()
//    }
//
//    private fun setupView() {
//        viewModel.titleBar.postValue("Layanan Darurat")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        viewModel.getDataRS()
//    }
//
//    private fun setupObserver() {
//        observe(viewModel.dataRumahSakit) {
//            adapter.setData(it)
//        }
//        observe(viewModel.message) { message ->
//            showToast(message)
//        }
//    }
//
//    private fun setupRecyclerView() {
//        adapter = DataRSAdapter(arrayListOf(), object : DataRSAdapter.OnAdapterListener {
//            override fun onClick(result: DataRSResponse) {
//                findNavController().navigate(
//                    R.id.action_lokasiFragment_to_sendMessageFragment,
//                    bundleOf(
//                        "nama_rs" to result.nama_rumah_sakit,
//                        "alamat" to result.alamat,
//                        "nomor" to result.nomer_telepon
//                    )
//                )
//            }
//        })
//    }

}