package com.example.notes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

class NotesBDHelper(
    context: Context?,
    private var DB_NAME: String = "notes.db",
    private var DB_VERSION: Int = 1
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NotesContract.NotesEntry().CREATE_COMMAND)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(NotesContract.NotesEntry().DROP_COMMAND)
        onCreate(db)
    }

}
