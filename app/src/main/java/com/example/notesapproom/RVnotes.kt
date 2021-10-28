package com.example.notesapproom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapproom.Room.Notes
import com.example.notesapproom.databinding.ItemNoteBinding

class RVnotes(val activity: MainActivity, var notesArray: List<Notes>) : RecyclerView.Adapter<RVnotes.ViewHolder>() {
    class ViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aNote = notesArray[position]
        holder.binding.apply {
            tvNote.text = aNote.note
            ibEdit.setOnClickListener {
                activity.alertUbdate(aNote)
            }
            ibDelete.setOnClickListener{
                activity.alertDelete(aNote)
            }
        }
    }

    override fun getItemCount(): Int {
        return notesArray.size
    }

}