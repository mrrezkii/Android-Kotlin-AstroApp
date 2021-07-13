package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.viewmodel.ProfilViewModel
import com.kedirilagi.astro.data.viewmodel.factory.ProfilViewModelFactory
import com.kedirilagi.astro.databinding.ActivityRiwayatJatuhBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RiwayatJatuhActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ProfilViewModelFactory by instance()
    private lateinit var viewModel: ProfilViewModel

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
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfilViewModel::class.java)
    }

    private fun setupView() {
        binding.tvToolbar.text = getString(R.string.riwayat_jatuh)
        binding.tvBack.setOnClickListener {
            finish()
        }
    }
}