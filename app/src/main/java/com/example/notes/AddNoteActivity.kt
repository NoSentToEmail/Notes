package com.example.notes

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.RadioGroup
import android.widget.Toast


class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTexDescription: EditText
    private lateinit var spinnerDaysOfWeek: Spinner
    private lateinit var radioGroupPriority: RadioGroup
    private lateinit var buttonSaveNote: Button

    private lateinit var dbHelder: NotesBDHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTexDescription = findViewById(R.id.editTexDescriotion)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        radioGroupPriority = findViewById(R.id.radioGroupPriority)
        buttonSaveNote = findViewById(R.id.buttonSaveNote)

        dbHelder = NotesBDHelper(this)
        database = dbHelder.writableDatabase

        Log.i("Blaha", "в активити кнопки добавления")

        buttonSaveNote.setOnClickListener() {
            Log.i("Blaha", "в самой кнопке добавления")
            val title = editTextTitle.text.toString().trim()
            val descriotion = editTexDescription.text.toString().trim()
            val dayOfWeek = spinnerDaysOfWeek.selectedItemPosition
            val radioButtonID = radioGroupPriority.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(radioButtonID)
            val priority = when (radioButton.text.toString()) {
                "Не срочно" -> 1
                "Умерено" -> 2
                "Срочно" -> 3
                else -> 0
            }

//            val priority:Int = Integer.parseInt(radioButton.text.toString())


            if(isFilled(title,descriotion)){
                val contentValue = ContentValues()
                contentValue.put(NotesContract.NotesEntry().COLUMN_TITLE, title)
                contentValue.put(NotesContract.NotesEntry().COLUMN_DESCRIPTION, descriotion)
                contentValue.put(NotesContract.NotesEntry().COLUMN_DAY_OF_WEEK, dayOfWeek +1)
                contentValue.put(NotesContract.NotesEntry().COLUMN_PRIORITY, priority)
                database.insert(NotesContract.NotesEntry().TABLE_NAME, null, contentValue)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,getString(R.string.warning_fill_fils), Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun isFilled(title: String, description: String): Boolean {
        return !title.isEmpty() && !description.isEmpty()
    }
}