package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.view.adapter.AboutAdapter
import com.kedirilagi.astro.data.viewmodel.ProfilViewModel
import com.kedirilagi.astro.data.viewmodel.factory.ProfilViewModelFactory
import com.kedirilagi.astro.databinding.ActivityTentangAstroBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TentangAstroActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ProfilViewModelFactory by instance()
    private lateinit var viewModel: ProfilViewModel
    private val binding: ActivityTentangAstroBinding by lazy {
        ActivityTentangAstroBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfilViewModel::class.java)
    }

    private fun setupView() {
        binding.tvToolbar.text = getString(R.string.tentang_astro)
        binding.tvBack.setOnClickListener {
            finish()
        }
        val adapter = AboutAdapter(supportFragmentManager, lifecycle)
        binding.viewpager.adapter = adapter
        binding.ivPrev.setOnClickListener {
            var tab: Int = binding.viewpager.currentItem
            if (tab != 0) {
                tab--
                binding.viewpager.currentItem = tab
            } else {
                binding.viewpager.currentItem = tab
            }
        }
        binding.ivNext.setOnClickListener {
            var tab: Int = binding.viewpager.currentItem
            tab++
            binding.viewpager.currentItem = tab
        }
    }
}