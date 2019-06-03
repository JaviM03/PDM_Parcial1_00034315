package com.example.parcial1.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity(tableName = "match_table")
data class Match(
    @PrimaryKey(autoGenerate = true) var id: Int,
        val match:String,
        var TeamA:String,
        var TeamB:String,
        var ScoreA:Int,
        var ScoreB:Int,

        var MatchEnd: Boolean,
        var date: Date


)