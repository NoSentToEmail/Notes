package com.example.notes

class note(
    private var title: String,
    private var descriotion:String,
    private var dayOfWeek:String,
    private var priority: Int
    ){

    fun getTitle(): String {
        return title
    }
    fun getDescriotion(): String {
        return descriotion
    }
    fun getDayOfWeek(): String {
        return dayOfWeek
    }
    fun getPriority(): Int {
        return priority
    }

}