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

class MainActivity : AppCompatActivity(), OnNoteClickListener {

    private lateinit var recyclerViewNotes: RecyclerView
    private var notes: ArrayList<note> = ArrayList()
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var dbHelper: NotesBDHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = NotesBDHelper(this)
        database = dbHelper.writableDatabase
        getData()
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes)
        val buttonAdd: FloatingActionButton = findViewById(R.id.buton_add_note)

        notes = ArrayList()

        val cursor:Cursor = database.query(NotesContract.NotesEntry().TABLE_NAME, null,
            null,null,null,null,null)

        while(cursor.moveToNext()){
            var id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_DESCRIPTION))
            val day_of_week = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_DAY_OF_WEEK))
            val priority: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_PRIORITY))
            val note:note = note(id, title,description,day_of_week, priority)
            notes.add(note)
        }
        cursor.close()

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
                    val id = notes.get(position).getId()
                    val where = BaseColumns._ID + "=?"
                    val whereArgs: String = Integer.toString(id)
                    database.delete(NotesContract.NotesEntry().TABLE_NAME, where, arrayOf(whereArgs))
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
        return true

    }
    private fun getData(){
        notes.clear()
        val cursor:Cursor = database.query(NotesContract.NotesEntry().TABLE_NAME, null,
            null,null,null,null,null)

        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_DESCRIPTION))
            val day_of_week = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_DAY_OF_WEEK))
            val priority: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry().COLUMN_PRIORITY))
            val note:note = note(id, title,description,day_of_week, priority)
            notes.add(note)
        }
        cursor.close()
    }
}
