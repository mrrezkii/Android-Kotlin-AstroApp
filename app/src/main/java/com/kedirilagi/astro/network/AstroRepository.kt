package com.kedirilagi.astro.network

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kedirilagi.astro.extension.post
import com.kedirilagi.astro.network.response.DataRSResponse
import com.kedirilagi.astro.network.response.RiwayatAktivitasResponse
import com.kedirilagi.astro.network.response.StatusPasienResponse
import com.kedirilagi.astro.storage.persistence.AstroDatabase
import com.kedirilagi.astro.storage.persistence.JadwalEntity
import com.kedirilagi.astro.storage.preferences.AstroPreferences
import com.kedirilagi.astro.storage.preferences.PreferencesOnboardingModel
import com.kedirilagi.astro.storage.preferences.prefFirst
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class AstroRepository(
    private val pref: AstroPreferences,
    private val db: AstroDatabase
) {
    companion object {
        const val TABLE_RIWAYAT_AKTIVITAS = "Riwayat Aktivitas"
        const val TABLE_STATUS_PASIEN = "Status Pasien"
        const val TABLE_DATA_RS = "Data Rumah Sakit"
    }

    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    val riwayatAktivitas: LiveData<List<RiwayatAktivitasResponse>> = MutableLiveData()
    val statusPasien: LiveData<List<StatusPasienResponse>> = MutableLiveData()
    val lastStatusPasien: LiveData<List<StatusPasienResponse>> = MutableLiveData()
    val dataRumahSakit: LiveData<List<DataRSResponse>> = MutableLiveData()

    val isLoading: LiveData<Boolean> = MutableLiveData()
    val message: LiveData<String> = MutableLiveData()

    fun savePreferencesOnboarding(first: Boolean?) {
        pref.put(prefFirst, first!!)
    }

    fun getPreferencesOnboarding(): PreferencesOnboardingModel {
        return PreferencesOnboardingModel(pref.getBoolean(prefFirst))
    }

    suspend fun saveDataJadwal(entity: JadwalEntity) {
        db.jadwalDao().insert(entity)
    }

    fun getDataJadwal() = db.jadwalDao().select()

    fun getFirstDataJadwal() = db.jadwalDao().selectFirstData()

    suspend fun deleteDataJadwal(id: String) {
        db.jadwalDao().deleteById(id)
    }

    private fun getRiwayatAktivitasValueListener() = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            isLoading.post(false)
            message.post("$error")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onDataChange(snapshot: DataSnapshot) {
            isLoading.post(false)

            val tanggal = LocalDate.now().toString()
            val riwayatAktivitasResult = mutableListOf<RiwayatAktivitasResponse>()
            snapshot.children.forEach { dataSnapshot ->
                dataSnapshot.getValue(RiwayatAktivitasResponse::class.java)?.let { aktivitas ->
                    aktivitas.key = dataSnapshot.key
                    aktivitas.nama_akvitias = dataSnapshot.child("nama_aktivitas").value.toString()
                    aktivitas.jam = dataSnapshot.child("jam").value.toString()
                    aktivitas.tanggal = dataSnapshot.child("tanggal").value.toString()
                    if (aktivitas.tanggal.toString() == tanggal) {
                        riwayatAktivitasResult.add(
                            RiwayatAktivitasResponse(
                                key = aktivitas.key,
                                nama_akvitias = aktivitas.nama_akvitias,
                                jam = aktivitas.jam,
                                tanggal = aktivitas.tanggal
                            )
                        )
                    }
                }
            }
            riwayatAktivitas.post(riwayatAktivitasResult)
        }
    }

    suspend fun getRiwayatAktivitas() = withContext(Dispatchers.IO) {
        isLoading.post(true)
        val ref = firebaseDatabase
            .reference
            .child(TABLE_RIWAYAT_AKTIVITAS)
        ref.addValueEventListener(getRiwayatAktivitasValueListener())
        ref.keepSynced(true)
    }

    private fun getStatusPasienValueListener() = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            isLoading.post(false)
            message.post("$error")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onDataChange(snapshot: DataSnapshot) {
            isLoading.post(false)

            val statusPasienResult = mutableListOf<StatusPasienResponse>()
            snapshot.children.forEach { dataSnapshot ->
                dataSnapshot.getValue(StatusPasienResponse::class.java)?.let { aktivitas ->
                    aktivitas.key = dataSnapshot.key
                    aktivitas.kondisi = dataSnapshot.child("kondisi").value.toString()
                    aktivitas.jam = dataSnapshot.child("jam").value.toString()
                    aktivitas.tanggal = dataSnapshot.child("tanggal").value.toString()
                    if (aktivitas.kondisi != "Aman") {
                        statusPasienResult.add(
                            StatusPasienResponse(
                                key = aktivitas.key,
                                kondisi = aktivitas.kondisi,
                                jam = aktivitas.jam,
                                tanggal = aktivitas.tanggal
                            )
                        )
                    }
                }
            }
            statusPasien.post(statusPasienResult)
        }
    }

    suspend fun getStatusPasien() = withContext(Dispatchers.IO) {
        isLoading.post(true)
        val ref = firebaseDatabase
            .reference
            .child(TABLE_STATUS_PASIEN)
        ref.addValueEventListener(getStatusPasienValueListener())
        ref.keepSynced(true)
    }

    private fun getLastStatusPasienValueListener() = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            isLoading.post(false)
            message.post("$error")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onDataChange(snapshot: DataSnapshot) {
            isLoading.post(false)

            val statusPasienResult = mutableListOf<StatusPasienResponse>()
            snapshot.children.forEach { dataSnapshot ->
                dataSnapshot.getValue(StatusPasienResponse::class.java)?.let { aktivitas ->
                    aktivitas.key = dataSnapshot.key
                    aktivitas.kondisi = dataSnapshot.child("kondisi").value.toString()
                    aktivitas.jam = dataSnapshot.child("jam").value.toString()
                    aktivitas.tanggal = dataSnapshot.child("tanggal").value.toString()
                    statusPasienResult.add(
                        StatusPasienResponse(
                            key = aktivitas.key,
                            kondisi = aktivitas.kondisi,
                            jam = aktivitas.jam,
                            tanggal = aktivitas.tanggal
                        )
                    )
                }
            }
            lastStatusPasien.post(statusPasienResult)
        }
    }

    suspend fun getLastStatusPasien() = withContext(Dispatchers.IO) {
        isLoading.post(true)
        val ref = firebaseDatabase
            .reference
            .child(TABLE_STATUS_PASIEN)
            .orderByKey()
            .limitToLast(1)
        ref.addValueEventListener(getLastStatusPasienValueListener())
        ref.keepSynced(true)
    }

    private fun getDataRSValueListener() = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            isLoading.post(false)
            message.post("$error")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onDataChange(snapshot: DataSnapshot) {
            isLoading.post(false)

            val dataRSResult = mutableListOf<DataRSResponse>()
            snapshot.children.forEach { dataSnapshot ->
                dataSnapshot.getValue(DataRSResponse::class.java)?.let { data ->
                    data.key = dataSnapshot.key
                    data.nama_rumah_sakit = dataSnapshot.child("nama_rumah_sakit").value.toString()
                    data.nomer_telepon = dataSnapshot.child("nomer_telepon").value.toString()
                    data.alamat = dataSnapshot.child("alamat").value.toString()
                    dataRSResult.add(
                        DataRSResponse(
                            key = data.key,
                            nama_rumah_sakit = data.nama_rumah_sakit,
                            nomer_telepon = data.nomer_telepon,
                            alamat = data.alamat
                        )
                    )
                }
            }
            dataRumahSakit.post(dataRSResult)
        }
    }

    suspend fun getDataRS() = withContext(Dispatchers.IO) {
        isLoading.post(true)
        val ref = firebaseDatabase
            .reference
            .child(TABLE_DATA_RS)
        ref.addValueEventListener(getDataRSValueListener())
        ref.keepSynced(true)
    }

}