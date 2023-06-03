package com.example.notes

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "<notes>")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var description: String,
    var dayOfWeek: Int,
    var priority: Int
) {
    @Ignore
    constructor(title: String, description: String, dayOfWeek: Int, priority: Int) :
            this(0, title, description, dayOfWeek, priority)


    fun getDayAsString(position: Int): String {
        return when (position) {
            1 -> "Понедельник"
            2 -> "Вторник"
            3 -> "Среда"
            4 -> "Четверг"
            5 -> "Пятница"
            6 -> "Суббота"
            7 -> "Воскресенье"
            else -> ""
        }
    }
}
