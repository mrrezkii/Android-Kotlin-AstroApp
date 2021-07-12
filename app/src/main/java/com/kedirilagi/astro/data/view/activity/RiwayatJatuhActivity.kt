package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kedirilagi.astro.databinding.ActivityRiwayatJatuhBinding

class RiwayatJatuhActivity : AppCompatActivity() {

    private val binding: ActivityRiwayatJatuhBinding by lazy {
        ActivityRiwayatJatuhBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.tvBack.setOnClickListener {
            finish()
        }
    }
}