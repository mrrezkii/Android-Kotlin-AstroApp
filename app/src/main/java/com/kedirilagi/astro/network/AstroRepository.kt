package com.kedirilagi.astro.network

import com.kedirilagi.astro.storage.persistence.AstroDatabase
import com.kedirilagi.astro.storage.persistence.JadwalEntity
import com.kedirilagi.astro.storage.preferences.AstroPreferences
import com.kedirilagi.astro.storage.preferences.PreferencesModelOnboarding
import com.kedirilagi.astro.storage.preferences.prefFirst

class AstroRepository(
    private val pref: AstroPreferences,
    private val db: AstroDatabase
) {

    fun savePreferencesOnboarding(first: Boolean?) {
        pref.put(prefFirst, first!!)
    }

    fun getPreferencesOnboarding(): PreferencesModelOnboarding {
        return PreferencesModelOnboarding(pref.getBoolean(prefFirst))
    }

    suspend fun saveDataJadwal(entity: JadwalEntity) {
        db.jadwalDao().insert(entity)
    }

    fun getDataJadwal() = db.jadwalDao().select()

    suspend fun deleteDataJadwa(entity: JadwalEntity) {
        db.jadwalDao().delete(entity)
    }
}