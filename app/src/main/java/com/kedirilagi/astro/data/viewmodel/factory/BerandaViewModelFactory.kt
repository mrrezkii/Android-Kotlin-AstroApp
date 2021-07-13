package com.kedirilagi.astro.data.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.BerandaViewModel
import com.kedirilagi.astro.network.AstroRepository

class BerandaViewModelFactory(
    private val repository: AstroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BerandaViewModel(repository) as T
    }
}