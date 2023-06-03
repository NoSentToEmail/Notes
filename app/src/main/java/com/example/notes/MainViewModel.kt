package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var database: NotesDatabase

    private lateinit var notes: LiveData<List<Note>>


    init {
        database = NotesDatabase.getInstance(application)
        notes = database.notesDao().getAllNotes()
    }

    fun getNotes(): LiveData<List<Note>>{
        return notes
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        database.notesDao().insertNote(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        database.notesDao().deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        database.notesDao().deleteAllNotes()
    }



}