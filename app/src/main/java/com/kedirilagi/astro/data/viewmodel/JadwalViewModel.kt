package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kedirilagi.astro.data.model.JadwalModel
import com.kedirilagi.astro.network.AstroRepository
import com.kedirilagi.astro.storage.persistence.JadwalEntity
import kotlinx.coroutines.launch

class JadwalViewModel(
    val repository: AstroRepository
) : ViewModel() {

    val getDataJadwal: LiveData<List<JadwalEntity>> = repository.getDataJadwal()

    fun saveDataJadwal(data: JadwalModel) = viewModelScope.launch {
        repository.saveDataJadwal(
            JadwalEntity(
                id = data.id,
                lokasi = data.lokasi,
                aktivitas = data.aktivitas,
                jam = data.jam,
                tanggal = data.tanggal
            )
        )
    }

    fun deleteDataJadwal(id: String) = viewModelScope.launch {
        repository.deleteDataJadwal(id)
    }

}