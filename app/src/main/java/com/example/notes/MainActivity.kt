package com.example.notes

import note
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(InternalCoroutinesApi::class)
class MainActivity : AppCompatActivity(), OnNoteClickListener {

    private lateinit var recyclerViewNotes: RecyclerView
    private var notes: ArrayList<note> = ArrayList()
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var database: NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = NotesDatabase.getInstance(this)

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
        val buttonAdd: FloatingActionButton = findViewById(R.id.buton_add_note)


        getData()

        notesAdapter = NotesAdapter(this, notes)
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
                    val note = notes.get(position)
                    database.notesDao().deleteNote(note)
                    getData()
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

        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("noteTitle", selectedNote.getTitle())
        intent.putExtra("noteDescription", selectedNote.getDescriotion())
        intent.putExtra("noteDayOfWeek", selectedNote.getDayOfWeek())
        intent.putExtra("notePriority", selectedNote.getPriority())
        startActivity(intent)

        return true

    }
    private fun getData(){
        val noteFromDB: List<note> = database.notesDao().getAllNotes
        notes.clear()
        notes.addAll(noteFromDB)
    }
}
