package com.kedirilagi.astro.data.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kedirilagi.astro.data.viewmodel.MainViewModel
import com.kedirilagi.astro.network.AstroRepository

class MainViewModelFactory(
    private val repository: AstroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}