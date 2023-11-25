package com.example.diroomdatabase.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diroomdatabase.utils.Constants.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var noteId:Int =0,
    @ColumnInfo(name = "note_title")
    var noteTitle: String="",
    @ColumnInfo(name = "note_description")
    var notDesc: String =""
)