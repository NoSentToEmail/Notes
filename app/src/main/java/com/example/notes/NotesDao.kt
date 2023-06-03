package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.NotesDatabase

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY dayOfWeek DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()
}