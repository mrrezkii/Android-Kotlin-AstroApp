package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository

class JadwalViewModel(
    val repository: AstroRepository
) : ViewModel()