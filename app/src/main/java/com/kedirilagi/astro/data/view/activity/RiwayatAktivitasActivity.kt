package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.viewmodel.ProfilViewModel
import com.kedirilagi.astro.data.viewmodel.factory.ProfilViewModelFactory
import com.kedirilagi.astro.databinding.ActivityRiwayatAktivitasBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RiwayatAktivitasActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ProfilViewModelFactory by instance()
    private lateinit var viewModel: ProfilViewModel

    private val binding: ActivityRiwayatAktivitasBinding by lazy {
        ActivityRiwayatAktivitasBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfilViewModel::class.java)
    }

    private fun setupView() {
        binding.tvToolbar.text = getString(R.string.riwayat_aktivitas)
        binding.tvBack.setOnClickListener {
            finish()
        }
    }
}