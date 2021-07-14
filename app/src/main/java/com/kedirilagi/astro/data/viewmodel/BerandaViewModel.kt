package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository
import com.kedirilagi.astro.storage.persistence.JadwalEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BerandaViewModel(
    val repository: AstroRepository
) : ViewModel() {
    val riwayatAktivitas = repository.riwayatAktivitas
    val message = repository.message
    val isLoading = repository.isLoading

    val getFirstDataJadwal: LiveData<JadwalEntity> = repository.getFirstDataJadwal()

    fun getRiwayatAktivitas() = CoroutineScope(Dispatchers.IO).launch {
        repository.getRiwayatAktivitas()
    }
}