package com.kedirilagi.astro.data.view.fragment.panicbutton

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.adapter.DataRSAdapter
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.databinding.FragmentDetailLokasiBinding
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.network.response.DataRSResponse
import com.kedirilagi.astro.utils.showToast


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupListener() {
        binding.layoutBottomSheet.searchView.doAfterTextChanged {
            adapter.filter.filter(it.toString())
        }
        binding.layoutBottomSheet.listLokasi.adapter = adapter
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Lokasi Rumah Sakit")
        binding.layoutBottomSheet.searchView.post {
            showKeyboard(
                context,
                binding.layoutBottomSheet.searchView
            )
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
                    R.id.action_detailLokasiFragment_to_sendMessageFragment,
                    bundleOf(
                        "nama_rs" to result.nama_rumah_sakit,
                        "alamat" to result.alamat,
                        "nomor" to result.nomer_telepon
                    )
                )
            }
        })
    }

    fun showKeyboard(mContext: Context?, view: View?) {
        if (mContext != null && view != null && view.requestFocus()) {
            val inputMethodManager =
                mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun closeKeyboard(mContext: Context?, view: View?) {
        if (mContext != null && view != null && view.requestFocus()) {
            val inputMethodManager =
                mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onPause() {
        super.onPause()
        closeKeyboard(context, binding.layoutBottomSheet.searchView)
    }

}