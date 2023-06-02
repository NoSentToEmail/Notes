package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


interface OnNoteClickListener {
    fun onNoteClick(position: Int)
    fun onLongCkick(position: Int) : Boolean
}

class NotesAdapter(private val context: Context, var notes: ArrayList<Note>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var onNoteClickListener: OnNoteClickListener? = null

    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        var textViewDayOfWeek: TextView = itemView.findViewById(R.id.textViewDayOfWeek)

        init {
            itemView.setOnClickListener {
                onNoteClickListener?.onNoteClick(adapterPosition)
            }
            itemView.setOnLongClickListener {
                onNoteClickListener?.onLongCkick(adapterPosition) ?: false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.textViewTitle.text = note.getTitle()
        holder.textViewDescription.text = note.getDescriotion()
        holder.textViewDayOfWeek.text = note.getDayAsString(note.getDayOfWeek() +1)
        var colorID = 0
        val priority = note.getPriority()
        when (priority) {
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
