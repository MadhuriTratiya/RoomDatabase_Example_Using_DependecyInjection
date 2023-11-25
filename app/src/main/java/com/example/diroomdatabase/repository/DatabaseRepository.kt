package com.example.diroomdatabase.repository

import com.example.diroomdatabase.db.NoteDao
import com.example.diroomdatabase.db.NoteEntity

//Repository is a custom class which provide database to   data access method

class DatabaseRepository(private val dao :NoteDao) {
    suspend fun saveNote(noteEntity: NoteEntity) =dao.saveNote(noteEntity)
    fun getAllNotes() = dao.getAllNotes()
}