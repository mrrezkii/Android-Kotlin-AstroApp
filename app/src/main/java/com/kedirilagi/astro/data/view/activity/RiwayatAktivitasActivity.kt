package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kedirilagi.astro.R
import com.kedirilagi.astro.databinding.ActivityRiwayatAktivitasBinding

class RiwayatAktivitasActivity : AppCompatActivity() {

    private val binding: ActivityRiwayatAktivitasBinding by lazy {
        ActivityRiwayatAktivitasBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.tvToolbar.text = getString(R.string.riwayat_aktivitas)
        binding.tvBack.setOnClickListener {
            finish()
        }
    }
}