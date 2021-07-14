package com.kedirilagi.astro.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kedirilagi.astro.extension.post
import com.kedirilagi.astro.network.response.RiwayatAktivitasResponse
import com.kedirilagi.astro.storage.persistence.AstroDatabase
import com.kedirilagi.astro.storage.persistence.JadwalEntity
import com.kedirilagi.astro.storage.preferences.AstroPreferences
import com.kedirilagi.astro.storage.preferences.PreferencesModelOnboarding
import com.kedirilagi.astro.storage.preferences.prefFirst
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AstroRepository(
    private val pref: AstroPreferences,
    private val db: AstroDatabase
) {

    private val firebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    val riwayatAktivitas: LiveData<List<RiwayatAktivitasResponse>> = MutableLiveData()
    val isLoading: LiveData<Boolean> = MutableLiveData()
    val message: LiveData<String> = MutableLiveData()

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

    suspend fun deleteDataJadwal(id: String) {
        db.jadwalDao().deleteById(id)
    }

    private fun getRiwayatAktivitasValueListener() = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            isLoading.post(false)
            message.post("$error")
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            isLoading.post(false)

            val riwayatAktivitasResult = mutableListOf<RiwayatAktivitasResponse>()
            snapshot.children.forEach { dataSnapshot ->
                dataSnapshot.getValue(RiwayatAktivitasResponse::class.java)?.let { aktivitas ->
                    aktivitas.key = dataSnapshot.key
                    aktivitas.nama_akvitias = dataSnapshot.child("nama_aktivitas").value.toString()
                    aktivitas.jam = dataSnapshot.child("Jam").value.toString()
                    riwayatAktivitasResult.add(
                        RiwayatAktivitasResponse(
                            key = aktivitas.key,
                            nama_akvitias = aktivitas.nama_akvitias,
                            jam = aktivitas.jam
                        )
                    )
                }
            }
            riwayatAktivitas.post(riwayatAktivitasResult)
        }
    }

    suspend fun getRiwayatAktivitas() = withContext(Dispatchers.IO) {
        isLoading.post(true)
        firebaseDatabase
            .reference
            .child(TABLE_RIWAYAT_AKTIVITAS)
            .addValueEventListener(getRiwayatAktivitasValueListener())
    }

    companion object {
        const val TABLE_RIWAYAT_AKTIVITAS = "Riwayat Aktivitas"
    }
}