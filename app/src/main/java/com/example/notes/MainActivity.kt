package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var notes: ArrayList<note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)

        notes = ArrayList()
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))
        notes.add(note("Парехмахер", "Сделать прическу", "понедельник", 3))

        val adapter = NotesAdapter( this, notes)
        recyclerViewNotes.adapter = adapter
        recyclerViewNotes.layoutManager = GridLayoutManager(this, 3)

    }
}