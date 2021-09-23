package com.example.notes

import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//applicaion means activity like context and activity as context

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NoteRepository
    val allNotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNode(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNode(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }


}