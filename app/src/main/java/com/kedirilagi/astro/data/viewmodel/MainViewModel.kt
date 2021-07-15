package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository
import com.kedirilagi.astro.storage.preferences.PreferencesModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: AstroRepository
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val preferences: MutableLiveData<PreferencesModel> = MutableLiveData()
    val dataRumahSakit = repository.dataRumahSakit
    val message = repository.message

    fun getPreferences() {
        preferences.value = repository.getPreferencesOnboarding()
    }

    fun savePrefFist(first: Boolean?) {
        repository.savePreferencesOnboarding(first)
    }

    fun getDataRS() = CoroutineScope(Dispatchers.IO).launch {
        repository.getDataRS()
    }
}