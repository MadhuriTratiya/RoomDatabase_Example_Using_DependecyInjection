@file:Suppress("Since15")

package com.example.diroomdatabase.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diroomdatabase.utils.Constants.NOTE_TABLE
import kotlinx.coroutines.flow.Flow


//Database Access Object Where we can define database interaction
@Dao
interface NoteDao {
    //save note function
    @Insert(onConflict = OnConflictStrategy.REPLACE)//Replace data with same id in room database
    suspend fun saveNote(noteEntity: NoteEntity)

    //get AllNote Function
    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId DESC")
    fun getAllNotes(): Flow<MutableList<NoteEntity>>

}