package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, var notes: ArrayList<note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note: note = notes[position]
        holder.textViewTitle.text = note.getTitle()
        holder.textViewDescription.text = note.getDescriotion()
        holder.textViewDayOfWeek.text = note.getDayOfWeek()
        holder.textViewPriority.text = String.format("%s", note.getPriority())

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        var textViewDayOfWeek: TextView = itemView.findViewById(R.id.textViewDayOfWeek)
        var textViewPriority: TextView = itemView.findViewById(R.id.textViewPriority)
    }
}