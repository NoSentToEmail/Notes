package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import note

class NotesAdapter(private val context: Context, var notes: ArrayList<note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        var textViewDayOfWeek: TextView = itemView.findViewById(R.id.textViewDayOfWeek)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note: note = notes[position]
        holder.textViewTitle.text = note.getTitle()
        holder.textViewDescription.text = note.getDescriotion()
        holder.textViewDayOfWeek.text = note.getDayOfWeek()
        var colorID = 0
        val priority = note.getPriority()
        when(priority)  {
            1 -> colorID = holder.itemView.resources.getColor(android.R.color.holo_green_light)
            2 -> colorID = holder.itemView.resources.getColor(android.R.color.holo_orange_light)
            3 -> colorID = holder.itemView.resources.getColor(R.color.red_malin)
        }
        holder.textViewTitle.setBackgroundColor(colorID)
    }

    override fun getItemCount(): Int {
        return notes.size
    }


}