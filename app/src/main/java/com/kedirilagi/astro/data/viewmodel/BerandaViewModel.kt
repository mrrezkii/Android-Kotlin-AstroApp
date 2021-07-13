package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository

class BerandaViewModel(
    val repository: AstroRepository
) : ViewModel()