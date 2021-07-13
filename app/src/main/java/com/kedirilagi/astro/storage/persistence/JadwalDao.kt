package com.kedirilagi.astro.storage.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JadwalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: JadwalEntity)

    @Update
    suspend fun update(entity: JadwalEntity)

    @Delete
    suspend fun delete(entity: JadwalEntity)

    @Query("DELETE FROM tableJadwal WHERE id = :user_id")
    suspend fun deleteById(user_id: String)

    @Query("DELETE FROM tableJadwal")
    suspend fun deleteAll()

    @Query("SELECT * FROM tableJadwal")
    fun select(): LiveData<List<JadwalEntity>>
}