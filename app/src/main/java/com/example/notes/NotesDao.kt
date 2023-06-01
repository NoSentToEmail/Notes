package com.example.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import note

@Dao
interface NotesDao {
    @get:Query("SELECT * FROM note ORDER BY dayOfWeek")
    val getAllNotes: List<note>

    @Insert
    fun insertNote(note: note)

    @Delete
    fun deleteNote(note: note)

    @Query("DELETE FROM note")
    fun deleteAllNotes()
}