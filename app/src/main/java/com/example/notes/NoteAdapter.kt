package com.example.notes

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val context: Context,val listerner : INotesAdapter ) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    val notes = ArrayList<Note>()

    class NoteViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        val textView : TextView = view.findViewById(R.id.text)
        val button : ImageView = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val viewHolder = NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))

        viewHolder.button.setOnClickListener {
            listerner.onClicked(notes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val current = notes[position]
        holder.textView.text = current.text
    }

    fun updateList(newList : List<Note>) {
        notes.clear()
        notes.addAll(newList)

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {

        return notes.size
    }


}
interface INotesAdapter {

    fun onClicked(note: Note)

}