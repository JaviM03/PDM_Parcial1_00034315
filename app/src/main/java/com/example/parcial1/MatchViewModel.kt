package com.example.parcial1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial1.database.MatchRoomDatabase
import com.example.parcial1.database.entities.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MatchViewModel(app: Application): AndroidViewModel(app) {
    val repository: MatchRepository
    val database: MatchRoomDatabase
    var getAllMatches: LiveData<List<Match>>
    lateinit var currentMatch: MediatorLiveData<Match>

    init {
        database = MatchRoomDatabase.getDatabase(app, viewModelScope)
        val matchDao = database.matchDao()
        repository = MatchRepository(matchDao)
        getAllMatches = repository.getAllMatches
        currentMatch = MediatorLiveData()
        currentMatch.value = Match(0,"0","A", "B", 0, 0, false,Calendar.getInstance().time)
    }

    fun insertMatch(match: Match) =  viewModelScope.launch(Dispatchers.IO){
        repository.insertMatch(match)
    }

    fun updateMatch(match: Match) =  viewModelScope.launch(Dispatchers.IO){
        repository.updateMatch(match)
    }


}