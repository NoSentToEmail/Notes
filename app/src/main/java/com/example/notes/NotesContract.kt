package com.example.notes

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

class NotesContract {
    class NotesEntry : BaseColumns {
        var TABLE_NAME = "notes"
        var COLUMN_TITLE = "title"
        var COLUMN_DESCRIPTION = "description"
        var COLUMN_DAY_OF_WEEK = "day_of_week"
        var COLUMN_PRIORITY = "priority"

        var TYPE_TEXT = "TEXT"
        var TYPE_INTEGER = "INTEGER"

        var CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($_ID $TYPE_INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE $TYPE_TEXT, $COLUMN_DESCRIPTION $TYPE_TEXT, $COLUMN_DAY_OF_WEEK $TYPE_TEXT, $COLUMN_PRIORITY $TYPE_INTEGER)"

        var DROP_COMMAND = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
