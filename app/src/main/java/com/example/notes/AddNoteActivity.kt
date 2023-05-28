package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.RadioGroup
import note
import androidx.cardview.widget.CardView



class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTexDescription: EditText
    private lateinit var spinnerDaysOfWeek: Spinner
    private lateinit var radioGroupPriority: RadioGroup
    private lateinit var buttonSaveNote: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTexDescription = findViewById(R.id.editTexDescriotion)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        radioGroupPriority = findViewById(R.id.radioGroupPriority)
        buttonSaveNote = findViewById(R.id.buttonSaveNote)

        Log.i("Blaha", "в активити кнопки добавления")

        buttonSaveNote.setOnClickListener() {
            Log.i("Blaha", "в самой кнопке добавления")
            val title = editTextTitle.text.toString().trim()
            val descriotion = editTexDescription.text.toString().trim()
            val dayOfWeek = spinnerDaysOfWeek.selectedItem.toString()
            val radioButtonID = radioGroupPriority.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(radioButtonID)
            val priority = when (radioButton.text.toString()) {
                "Не срочно" -> 1
                "Умерено" -> 2
                "Срочно" -> 3
                else -> 0
            }

//            val priority:Int = Integer.parseInt(radioButton.text.toString())

            val note: note = note(title, descriotion, dayOfWeek, priority)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("noteTitle", title)
            intent.putExtra("noteDescription", descriotion)
            intent.putExtra("noteDayOfWeek", dayOfWeek)
            intent.putExtra("notePriority", priority)
            startActivity(intent)
        }
    }
}