package com.example.notesapproom.Room

import androidx.room.*

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotesInfo(): List<Notes>

    @Insert
    fun insertNote(note: Notes)

    @Update
    fun updateNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)
}