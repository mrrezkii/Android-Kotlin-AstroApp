package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kedirilagi.astro.databinding.ActivityStatusBinding
import com.kedirilagi.astro.utils.viewHide
import com.kedirilagi.astro.utils.viewShow

class StatusActivity : AppCompatActivity() {

    private val kondisi by lazy { intent.getBooleanExtra("kondisi", false) }

    private val binding: ActivityStatusBinding by lazy {
        ActivityStatusBinding.inflate(
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

        if (!kondisi) {
            binding.layoutSafe.root.viewHide()
            binding.layoutEmergeny.root.viewShow()
        }
    }
}