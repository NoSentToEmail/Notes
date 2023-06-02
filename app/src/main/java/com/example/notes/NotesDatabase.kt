package com.example.notes

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.InternalCoroutinesApi
import com.example.notes.Note
import kotlin.math.log

@OptIn(InternalCoroutinesApi::class)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao
    companion object {

        private  var database: NotesDatabase ?= null

        private val DB_NAME = "notes2.db"
        private val LOCK: Any = Any()

        fun getInstance(context: Context): NotesDatabase {
            synchronized(LOCK) {
                if (database == null) {
                    database = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return database ?: throw IllegalStateException("Database instance is null")
        }
    }

}



