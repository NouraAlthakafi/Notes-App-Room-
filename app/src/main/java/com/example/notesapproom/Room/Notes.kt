package com.example.notesapproom.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id : Int? = 0,
    @ColumnInfo(name = "Note") val note: String
)