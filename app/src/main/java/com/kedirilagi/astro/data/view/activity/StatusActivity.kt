package com.kedirilagi.astro.data.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.BerandaViewModel
import com.kedirilagi.astro.data.viewmodel.factory.BerandaViewModelFactory
import com.kedirilagi.astro.databinding.ActivityStatusBinding
import com.kedirilagi.astro.extension.dayExtension
import com.kedirilagi.astro.extension.monthExtension
import com.kedirilagi.astro.extension.observe
import com.kedirilagi.astro.utils.showToast
import com.kedirilagi.astro.utils.viewHide
import com.kedirilagi.astro.utils.viewShow
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupViewModel()
        setupView()
        setupObserve()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLastStatusPasien()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(BerandaViewModel::class.java)
    }

    private fun setupView() {
        binding.tvBack.setOnClickListener {
            if (isTaskRoot) {
                startActivity(Intent(this@StatusActivity, MainActivity::class.java))
                finish()
                showToast("The root stack activity")
            } else {
                finish()
            }
        }

        if (!kondisi) {
            binding.layoutSafe.root.viewHide()
            binding.layoutEmergeny.root.viewShow()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupObserve() {
        observe(viewModel.statusPasien) {
            it.forEach {
                binding.layoutEmergeny.tvAktivitas.text = it.kondisi
                binding.layoutEmergeny.tvJam.text = it.jam

                val tanggal = LocalDate.parse(it.tanggal, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val hari = dayExtension(tanggal.year, tanggal.monthValue - 1, tanggal.dayOfMonth)
                val bulan = monthExtension(tanggal.year, tanggal.monthValue - 1, tanggal.dayOfMonth)
                val date = "$hari, ${tanggal.dayOfMonth} $bulan ${tanggal.year}"

                binding.layoutEmergeny.tvTanggal.text = date
            }
        }
        observe(viewModel.message) { message ->
            showToast(message)
        }
    }
}