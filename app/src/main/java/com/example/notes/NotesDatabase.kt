
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
    companion object {
        private val DB_NAME = "notes2.db"
        private val LOCK: Any = Object()
        private var database: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            Log.i("aaaaaaaaaaaaaaa", "вначале")
            synchronized(LOCK) {
                Log.i("aaaaaaaaaaaaaaa", "внутри")

                if (database == null) {
                    Log.i("aaaaaaaaaaaaaaa", "в ифке")

                    database = Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                    Log.i("aaaaaaaaaaaaaaa", "конец ифки")

                }
            }
            Log.i("aaaaaaaaaaaaaaa", "ушел")

            return database ?: throw IllegalStateException("Database instance is null")
        }
    }

    abstract fun notesDao(): NotesDao
}