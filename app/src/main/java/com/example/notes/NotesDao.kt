package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.NoteDatabase

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)
}