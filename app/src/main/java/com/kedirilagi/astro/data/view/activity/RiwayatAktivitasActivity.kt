package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.adapter.RiwayatAktivitasAdapter
import com.kedirilagi.astro.data.viewmodel.ProfilViewModel
import com.kedirilagi.astro.data.viewmodel.factory.ProfilViewModelFactory
import com.kedirilagi.astro.databinding.ActivityRiwayatAktivitasBinding
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.utils.showToast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class RiwayatAktivitasActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ProfilViewModelFactory by instance()
    private lateinit var viewModel: ProfilViewModel
    private lateinit var adapterAktivitas: RiwayatAktivitasAdapter

    private val binding: ActivityRiwayatAktivitasBinding by lazy {
        ActivityRiwayatAktivitasBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupViewModel()
        setupView()
        setupObserve()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfilViewModel::class.java)
    }

    private fun setupView() {
        setAdapterAktivitas()
        binding.tvToolbar.text = getString(R.string.riwayat_aktivitas)
        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    private fun setAdapterAktivitas() {
        adapterAktivitas = RiwayatAktivitasAdapter(arrayListOf())
        binding.listRiwayat.adapter = adapterAktivitas
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRiwayatAktivitas()
    }

    private fun setupObserve() {
        observe(viewModel.riwayatAktivitas) {
            Collections.reverse(it)
            adapterAktivitas.setData(it)
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
    }
}