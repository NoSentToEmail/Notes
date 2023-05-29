package com.example.notes

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import note

class MainActivity : AppCompatActivity(), OnNoteClickListener {

    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var notes: ArrayList<note>
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var dbHelper: NotesBDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = NotesBDHelper(this)
        val database: SQLiteDatabase = dbHelper.writableDatabase


        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
        val buttonAdd: FloatingActionButton = findViewById(R.id.buton_add_note)

        notes = ArrayList()
        notes.add(note("", "", "", 2))

        val intent = intent
        if (intent.hasExtra("noteTitle")) {
            val title = intent.getStringExtra("noteTitle")
            val description = intent.getStringExtra("noteDescription")
            val dayOfWeek = intent.getStringExtra("noteDayOfWeek")
            val priority = intent.getIntExtra("notePriority", 0)

            val note =
                note(title.toString(), description.toString(), dayOfWeek.toString(), priority)
            notes.add(note)
        }
        val notesFromDB = ArrayList<note>()
        for(note:note in notes){
            val contentValues = ContentValues()
            contentValues.put(NotesContract.NotesEntry().COLUMN_TITLE, note.getTitle())
            contentValues.put(NotesContract.NotesEntry().COLUMN_DESCRIPTION, note.getDescriotion())
            contentValues.put(NotesContract.NotesEntry().COLUMN_DAY_OF_WEEK, note.getDayOfWeek())
            contentValues.put(NotesContract.NotesEntry().COLUMN_PRIORITY, note.getPriority())
            database.insert(NotesContract.NotesEntry().TABLE_NAME, null, contentValues)
        }


        val cursol:Cursor = database.query(NotesContract.NotesEntry().TABLE_NAME, null,
            null,null,null,null,null,null)


        while(cursol.moveToNext()){
            val title = cursol.getString(cursol.getColumnIndex(NotesContract.NotesEntry().COLUMN_TITLE))
            val description = cursol.getString(cursol.getColumnIndex(NotesContract.NotesEntry().COLUMN_DESCRIPTION))
            val day_of_week = cursol.getString(cursol.getColumnIndex(NotesContract.NotesEntry().COLUMN_DAY_OF_WEEK))
            val priority: Int = cursol.getInt(cursol.getColumnIndex(NotesContract.NotesEntry().COLUMN_PRIORITY))
            val note:note = note(title,description,day_of_week, priority)
            notesFromDB.add(note)
        }
        cursol.close()

        notesAdapter = NotesAdapter(this, notesFromDB)
        notesAdapter.setOnNoteClickListener(this)
        recyclerViewNotes.adapter = notesAdapter
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)




        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra("noteTitle", "")
            intent.putExtra("noteDescription", "")
            intent.putExtra("noteDayOfWeek", "")
            intent.putExtra("notePriority", 2)
            startActivity(intent)
        }

        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    notesFromDB.removeAt(position)
                    notesAdapter.notifyItemRemoved(position)
                }
            })
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)
    }

    override fun onNoteClick(position: Int) {
        val clickedNote = notes[position]
        Toast.makeText(this, "Clicked note: быстрый клик${clickedNote.getPriority()}", Toast.LENGTH_SHORT).show()
    }

    override fun onLongCkick(position: Int): Boolean {
        val selectedNote = notes[position]
        Toast.makeText(this, "Clicked note: долгий клик ${selectedNote.getPriority()}", Toast.LENGTH_SHORT).show()
        return true

    }
}
