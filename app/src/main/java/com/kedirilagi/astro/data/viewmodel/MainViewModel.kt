package com.kedirilagi.astro.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kedirilagi.astro.network.AstroRepository
import com.kedirilagi.astro.storage.preferences.PreferencesModel

class MainViewModel(
    val repository: AstroRepository
) : ViewModel() {
    val preferences: MutableLiveData<PreferencesModel> = MutableLiveData()

    fun getPreferences() {
        preferences.value = repository.getPreferencesOnboarding()
    }

    fun savePrefFist(first: Boolean?) {
        repository.savePreferencesOnboarding(first)
    }
}