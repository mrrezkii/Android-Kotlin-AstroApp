package com.kedirilagi.astro.data.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kedirilagi.astro.constant.notifikasiDummy
import com.kedirilagi.astro.data.view.adapter.NotifikasiAdapter
import com.kedirilagi.astro.databinding.ActivityNotifikasiBinding

class NotifikasiActivity : AppCompatActivity() {

    private lateinit var adapter: NotifikasiAdapter

    private val binding: ActivityNotifikasiBinding by lazy {
        ActivityNotifikasiBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setRecyclerView()
    }

    private fun setupView() {
        binding.tvToolbar.text = "Pemberitahuan"
        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    private fun setRecyclerView() {
        adapter = NotifikasiAdapter(notifikasiDummy())
        binding.listNotifikasi.adapter = adapter
    }
}