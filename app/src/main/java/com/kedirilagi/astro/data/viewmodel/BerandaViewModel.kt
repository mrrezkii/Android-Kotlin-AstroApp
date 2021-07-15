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
    val statusPasien = repository.lastStatusPasien
    val message = repository.message

    val getFirstDataJadwal: LiveData<JadwalEntity> = repository.getFirstDataJadwal()

    fun getRiwayatAktivitas() = CoroutineScope(Dispatchers.IO).launch {
        repository.getRiwayatAktivitas()
    }

    fun getLastStatusPasien() = CoroutineScope(Dispatchers.IO).launch {
        repository.getLastStatusPasien()
    }
}