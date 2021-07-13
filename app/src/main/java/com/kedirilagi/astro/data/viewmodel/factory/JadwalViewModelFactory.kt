package com.kedirilagi.astro.data.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.JadwalViewModel
import com.kedirilagi.astro.network.AstroRepository

class JadwalViewModelFactory(
    private val repository: AstroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JadwalViewModel(repository) as T
    }
}