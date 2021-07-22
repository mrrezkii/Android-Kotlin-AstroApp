package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository
import com.kedirilagi.astro.storage.preferences.PreferencesCoordinateModel
import com.kedirilagi.astro.storage.preferences.PreferencesOnboardingModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: AstroRepository
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val preferencesOnboarding: MutableLiveData<PreferencesOnboardingModel> = MutableLiveData()
    val preferencesCoordinate: MutableLiveData<PreferencesCoordinateModel> = MutableLiveData()
    val dataRumahSakit = repository.dataRumahSakit
    val message = repository.message
    val statusPasien = repository.lastStatusPasien

    fun getPreferencesOnboarding() {
        preferencesOnboarding.value = repository.getPreferencesOnboarding()
    }

    fun getPreferencesCoordinate() {
        preferencesCoordinate.value = repository.getPreferencesCoodinate()
    }

    fun savePrefCoordinate(lat: String?, long: String?) {
        repository.savePreferencesCoodinate(lat, long)
    }

    fun savePrefFirst(first: Boolean?) {
        repository.savePreferencesOnboarding(first)
    }

    fun getDataRS() = CoroutineScope(Dispatchers.IO).launch {
        repository.getDataRS()
    }

    fun getLastStatusPasien() = CoroutineScope(Dispatchers.IO).launch {
        repository.getLastStatusPasien()
    }
}