package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.MainlViewModel
import com.kedirilagi.astro.data.viewmodel.factory.MainViewModelFactory
import com.kedirilagi.astro.databinding.ActivityStatusBinding
import com.kedirilagi.astro.utils.viewHide
import com.kedirilagi.astro.utils.viewShow
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StatusActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: MainViewModelFactory by instance()
    private lateinit var viewModel: MainlViewModel
    private val kondisi by lazy { intent.getBooleanExtra("kondisi", false) }

    private val binding: ActivityStatusBinding by lazy {
        ActivityStatusBinding.inflate(
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainlViewModel::class.java)
    }

    private fun setupView() {
        binding.tvBack.setOnClickListener {
            finish()
        }

        if (!kondisi) {
            binding.layoutSafe.root.viewHide()
            binding.layoutEmergeny.root.viewShow()
        }
    }
}