package com.kedirilagi.astro.storage.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableJadwal")
data class JadwalEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val aktivitas: String,
    val lokasi: String,
    val jam: String,
    val tanggal: String
)