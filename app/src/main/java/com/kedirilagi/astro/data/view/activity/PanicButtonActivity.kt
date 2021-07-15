package com.kedirilagi.astro.data.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.R
import com.kedirilagi.astro.data.viewmodel.PetunjukViewModel
import com.kedirilagi.astro.data.viewmodel.factory.PetunjukViewModelFactory
import com.kedirilagi.astro.databinding.ActivityPanicButtonBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PanicButtonActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: PetunjukViewModelFactory by instance()
    private lateinit var viewModel: PetunjukViewModel
    private val binding: ActivityPanicButtonBinding by lazy {
        ActivityPanicButtonBinding.inflate(
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(PetunjukViewModel::class.java)
    }

    private fun setupView() {
        binding.tvLayoutToolbar.tvToolbar.text = getString(R.string.petunjuk)
        binding.tvLayoutToolbar.ivBack.setOnClickListener {
            finish()
        }
    }

    @Suppress("DEPRECATION")
    override fun onStart() {
        super.onStart()
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)

        val view = window.decorView
        view.systemUiVisibility = 0
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @Suppress("DEPRECATION")
    override fun onPause() {
        super.onPause()
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        val view = window.decorView
        view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}