package com.example.notesapproom

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapproom.Room.Notes
import com.example.notesapproom.Room.NotesDatabase

class MainActivity : AppCompatActivity() {
    // Declaring UI Elements
    lateinit var rvNotes: RecyclerView
    lateinit var notesArray: List<Notes>
    lateinit var etNote: EditText
    lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Declaring UI Elements
        rvNotes = findViewById(R.id.rvNotes)
        notesArray = arrayListOf()
        etNote = findViewById(R.id.tvNote)
        btnSubmit = findViewById(R.id.btnSubmit)
        rvChange()
        btnSubmit.setOnClickListener {
            val note = etNote.text.toString()
            val save = Notes(null, note)
                NotesDatabase.getInstance(applicationContext).NotesDao().insertNote(save)
                rvChange()
            etNote.text.clear()
            etNote.clearFocus()
        }
    }
    fun alertUbdate(note: Notes) {
        val builder1 = AlertDialog.Builder(this)
        val noteUpdate = EditText(this)
        noteUpdate.hint = "Update note"
        builder1.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
            val newNote = noteUpdate.text.toString()
            var id = note.id

                NotesDatabase.getInstance(applicationContext).NotesDao().updateNote(Notes(id, newNote))
                rvChange()
        })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val edit = builder1.create()
        edit.setTitle("Edit")
        edit.setView(noteUpdate)
        edit.show()
    }

    fun delete(note: Notes) {
        NotesDatabase.getInstance(applicationContext).NotesDao().deleteNote(note)
        rvChange()
    }

    fun alertDelete(note: Notes){
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage("Are you sure?")
        builder1.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            delete(note)
        })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val delete = builder1.create()
        delete.setTitle("Delete")
        delete.show()
    }

    fun rvChange(){
        notesArray = NotesDatabase.getInstance(applicationContext).NotesDao().getAllNotesInfo()
        rvNotes.adapter = RVnotes(this, notesArray)
        rvNotes.layoutManager = LinearLayoutManager(this)
    }
}