package com.example.kotlin_notes_app.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_notes_app.R
import com.example.kotlin_notes_app.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

// get data
class NotesAdapter(val arrList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.tvTitle1.text = arrList[position].title
        holder.itemView.tvDesc.text = arrList[position].noteText
        holder.itemView.tvDateTime.text = arrList[position].dateTime

        if (arrList[position].color != null) {
            holder.itemView.cvNotes.setCardBackgroundColor(Color.parseColor(arrList[position].color))
        } else {
            holder.itemView.cvNotes.setCardBackgroundColor(Color.parseColor("#171C26"))
        }

        if (arrList[position].imgPath != null) {
            holder.itemView.ivNote.setImageBitmap(BitmapFactory.decodeFile(arrList[position].imgPath))
            holder.itemView.ivNote.visibility = View.VISIBLE
        } else {
            holder.itemView.ivNote.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}