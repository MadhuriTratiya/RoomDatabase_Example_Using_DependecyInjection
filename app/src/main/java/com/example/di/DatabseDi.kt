package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.diroomdatabase.db.NoteDatabase
import com.example.diroomdatabase.utils.Constants.NOTE_DATABASE

fun provideDatabase(context:Context) =
    Room.databaseBuilder(context, NoteDatabase::class.java,NOTE_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db : NoteDatabase) = db.noteDao()
