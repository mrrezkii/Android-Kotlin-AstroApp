package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfilViewModel(
    val repository: AstroRepository
) : ViewModel() {
    val riwayatAktivitas = repository.riwayatAktivitas
    val message = repository.message

    fun getRiwayatAktivitas() = CoroutineScope(Dispatchers.IO).launch {
        repository.getRiwayatAktivitas()
    }
}