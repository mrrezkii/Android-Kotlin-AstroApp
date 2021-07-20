package com.kedirilagi.astro.data.view.activity

import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.kedirilagi.astro.databinding.ActivityDetailArtikelBinding

class DetailArtikelActivity : AppCompatActivity() {

    private val judul by lazy { intent.getStringExtra("judul") }
    private val url by lazy { intent.getStringExtra("url") }

    private val binding: ActivityDetailArtikelBinding by lazy {
        ActivityDetailArtikelBinding.inflate(
            layoutInflater
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupView() {
        binding.tvToolbar.text = judul
        binding.tvBack.setOnClickListener {
            finish()
        }
        createWebView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createWebView() {
        val loadUrlString = url.toString()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            loadUrl(loadUrlString)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.webView.canGoBack()) binding.webView.goBack()
        else super.onBackPressed()
    }
}