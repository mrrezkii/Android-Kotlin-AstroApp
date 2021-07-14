package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository

class MainViewModel(
    val repository: AstroRepository
) : ViewModel()