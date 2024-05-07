package org.d3if3028.assessment1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3028.assessment1.model.Hewan

@Dao
interface HewanDao {
    @Insert
    suspend fun insert(hewan: Hewan)
    @Update
    suspend fun update(hewan: Hewan)
    @Query("SELECT * FROM hewan ORDER BY nama ASC")
    fun getHewanDao(): Flow<List<Hewan>>
    @Query("SELECT * FROM hewan WHERE id = :id")
    suspend fun getHewanById(id: Long): Hewan?
    @Query("DELETE FROM hewan WHERE id = :id")
    suspend fun deleteById(id: Long)
}