package com.kedirilagi.astro.network

import com.kedirilagi.astro.storage.preferences.AstroPreferences
import com.kedirilagi.astro.storage.preferences.PreferencesModelOnboarding
import com.kedirilagi.astro.storage.preferences.prefFirst

class AstroRepository(
    private val pref: AstroPreferences
) {

    fun savePreferencesOnboarding(first: Boolean?) {
        pref.put(prefFirst, first!!)
    }

    fun getPreferencesOnboarding(): PreferencesModelOnboarding {
        return PreferencesModelOnboarding(pref.getBoolean(prefFirst))
    }
}