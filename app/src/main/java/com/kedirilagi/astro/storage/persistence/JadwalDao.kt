package com.kedirilagi.astro.storage.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JadwalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: JadwalEntity)

    @Query("DELETE FROM tableJadwal WHERE id = :user_id")
    suspend fun deleteById(user_id: String)

    @Query("SELECT * FROM tableJadwal")
    fun select(): LiveData<List<JadwalEntity>>

    @Query("SELECT * FROM tableJadwal WHERE id LIMIT 1")
    fun selectFirstData(): LiveData<JadwalEntity>
}