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
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instance == null) {
                synchronized(NoteDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java, "note_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}



