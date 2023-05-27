package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import note

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewNotes: RecyclerView
    lateinit var notes: ArrayList<note>
    private lateinit var buttonAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
        buttonAdd = findViewById(R.id.buton_add_note)

        notes = ArrayList()


        val intent = intent

        if (intent.hasExtra("noteTitle")) {
            val title = intent.getStringExtra("noteTitle")
            val description = intent.getStringExtra("noteDescription")
            val dayOfWeek = intent.getStringExtra("noteDayOfWeek")
            val priority = intent.getIntExtra("notePriority", 0) // Здесь 0 - значение по умолчанию, если не удалось получить данные

            // Создаем новый объект Note с переданными данными
            val note = note(title.toString(), description.toString(), dayOfWeek.toString(), priority)
            notes.add(note)
        }



        val adapter = NotesAdapter( this, notes)
        recyclerViewNotes.adapter = adapter

        // Варианты отображения списка

//        recyclerViewNotes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
//        recyclerViewNotes.layoutManager = GridLayoutManager(this, 3)

        buttonAdd.setOnClickListener(){
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)

        }


    }
}