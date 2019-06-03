package com.example.parcial1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.parcial1.database.DAO.MatchDao
import com.example.parcial1.database.converters.Converters
import com.example.parcial1.database.entities.Match
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Match::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MatchRoomDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object {
        @Volatile
        private var INSTANCE: MatchRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MatchRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, MatchRoomDatabase::class.java, "Match_database")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
