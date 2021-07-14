package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.BerandaViewModel
import com.kedirilagi.astro.data.viewmodel.factory.BerandaViewModelFactory
import com.kedirilagi.astro.databinding.ActivityStatusBinding
import com.kedirilagi.astro.utils.viewHide
import com.kedirilagi.astro.utils.viewShow
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StatusActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: BerandaViewModelFactory by instance()
    private lateinit var viewModel: BerandaViewModel
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(BerandaViewModel::class.java)
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