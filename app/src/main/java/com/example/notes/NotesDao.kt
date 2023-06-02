package com.example.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @get:Query("SELECT * FROM note ORDER BY dayOfWeek")
    val getAllNotes: List<Note>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM note")
    fun deleteAllNotes()
}