package com.example.diroomdatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diroomdatabase.db.NoteEntity
import com.example.diroomdatabase.repository.DatabaseRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DatabaseViewModel (private val repository: DatabaseRepository) : ViewModel(){

    //internal variable
    private val _noteList= MutableLiveData<List<NoteEntity>>()
    //external variable
    val noteList : LiveData<List<NoteEntity>>
    //connection between internal and external variable
    get() = _noteList

    fun getAllNote() = viewModelScope.launch {
        repository.getAllNotes().collect(){
            _noteList.postValue(it)
        }
    }
    fun saveNote(noteEntity: NoteEntity)=viewModelScope.launch {
        repository.saveNote(noteEntity)
    }
}