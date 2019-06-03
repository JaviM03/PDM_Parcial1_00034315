package com.example.parcial1.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.parcial1.database.entities.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM match_table ORDER BY date ASC")
    fun getAllMatches(): LiveData<List<Match>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(match: Match)

    @Query("DELETE FROM match_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateMatch(match: Match)
}