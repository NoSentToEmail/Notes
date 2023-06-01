
package com.example.notes

import android.content.Context
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized
import note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.notes.NotesDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Database(entities = [note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    companion object {
        private val DB_NAME = "notes2.db"
        private val LOCK: Any = Object()
        private var database: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            synchronized(LOCK) {
                if (database == null) {
                    database = Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return database ?: throw IllegalStateException("Database instance is null")
        }
    }

    abstract fun notesDao(): NotesDao
}