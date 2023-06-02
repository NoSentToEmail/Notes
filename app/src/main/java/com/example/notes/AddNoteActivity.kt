package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(InternalCoroutinesApi::class)
class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTexDescription: EditText
    private lateinit var spinnerDaysOfWeek: Spinner
    private lateinit var radioGroupPriority: RadioGroup
    private lateinit var buttonSaveNote: Button

    private lateinit var database: NotesDatabase

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTexDescription = findViewById(R.id.editTexDescriotion)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        radioGroupPriority = findViewById(R.id.radioGroupPriority)
        buttonSaveNote = findViewById(R.id.buttonSaveNote)

        database = NotesDatabase.getInstance(this)



        buttonSaveNote.setOnClickListener() {
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


            if(isFilled(title, descriotion)){
                val note = Note(0, title, descriotion, dayOfWeek, priority)
                database.notesDao().insertNote(note)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, R.string.warning_fill_fils, Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun isFilled(title: String, description: String): Boolean {
        return !title.isEmpty() && !description.isEmpty()
    }
}