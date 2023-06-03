package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.InternalCoroutinesApi
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.notes.NoteDatabase


@OptIn(InternalCoroutinesApi::class)
class MainActivity : AppCompatActivity(), OnNoteClickListener {

    private lateinit var recyclerViewNotes: RecyclerView
    private var notes: ArrayList<Note> = ArrayList()
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var database: NoteDatabase
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = NoteDatabase.getInstance(this.applicationContext)
        val factory = NoteViewModel(application)

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
        val buttonAdd: FloatingActionButton = findViewById(R.id.buton_add_note)



        notesAdapter = NotesAdapter(this, notes)
        notesAdapter.setOnNoteClickListener(this)
        getData()
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
                    database.noteDao().deleteAllNotes()

                }
            })
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)

        getData()

    }

    override fun onNoteClick(position: Int) {
        val clickedNote = notes[position]
        Toast.makeText(this, "Clicked note: быстрый клик${clickedNote.priority}", Toast.LENGTH_SHORT).show()
    }

    override fun onLongCkick(position: Int): Boolean {
        val selectedNote = notes[position]
        Toast.makeText(this, "Clicked note: долгий клик ${selectedNote.priority}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("noteTitle", selectedNote.title)
        intent.putExtra("noteDescription", selectedNote.description)
        intent.putExtra("noteDayOfWeek", selectedNote.dayOfWeek)
        intent.putExtra("notePriority", selectedNote.priority)
        startActivity(intent)

        return true

    }
    private fun getData(){
        val noteFromDB: LiveData<List<Note>> = database.noteDao().getAllNotes()
        noteFromDB.observe(this,  Observer<List<Note>>() {
            notes.clear()
            notes.addAll(it)
            notesAdapter.notifyDataSetChanged()
        })
    }
}
