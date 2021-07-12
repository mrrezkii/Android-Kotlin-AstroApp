package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kedirilagi.astro.databinding.ActivityTentangAstroBinding

class TentangAstroActivity : AppCompatActivity() {

    private val binding: ActivityTentangAstroBinding by lazy {
        ActivityTentangAstroBinding.inflate(
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