package com.example.parcial1

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.parcial1.database.DAO.MatchDao
import com.example.parcial1.database.entities.Match

class MatchRepository(private val matchDao: MatchDao) {

    val getAllMatches: LiveData<List<Match>> = matchDao.getAllMatches()

    @WorkerThread
    suspend fun insertMatch(match: Match){
        matchDao.insert(match)
    }

    @WorkerThread
    suspend fun updateMatch(match: Match){
        matchDao.updateMatch(match)
    }

    @WorkerThread
    suspend fun deleteAll(match: Match){
        matchDao.deleteAll()
    }

}