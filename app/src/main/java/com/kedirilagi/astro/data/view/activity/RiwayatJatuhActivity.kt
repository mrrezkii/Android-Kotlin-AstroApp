package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.adapter.RiwayatJatuhAdapter
import com.kedirilagi.astro.data.viewmodel.ProfilViewModel
import com.kedirilagi.astro.data.viewmodel.factory.ProfilViewModelFactory
import com.kedirilagi.astro.databinding.ActivityRiwayatJatuhBinding
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.utils.showToast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class RiwayatJatuhActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ProfilViewModelFactory by instance()
    private lateinit var viewModel: ProfilViewModel
    private lateinit var adapterStatus: RiwayatJatuhAdapter

    private val binding: ActivityRiwayatJatuhBinding by lazy {
        ActivityRiwayatJatuhBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupView()
        setupObserve()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfilViewModel::class.java)
    }

    private fun setupView() {
        setAdapterAktivitas()
        binding.tvToolbar.text = getString(R.string.riwayat_jatuh)
        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    private fun setAdapterAktivitas() {
        adapterStatus = RiwayatJatuhAdapter(arrayListOf())
        binding.listRiwayat.adapter = adapterStatus
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStatusPasien()
    }

    private fun setupObserve() {
        observe(viewModel.statusPasien) {
            Collections.reverse(it)
            adapterStatus.setData(it)
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
    }
}