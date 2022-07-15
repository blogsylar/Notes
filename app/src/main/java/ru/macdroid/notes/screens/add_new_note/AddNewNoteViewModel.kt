package ru.macdroid.notes.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.macdroid.notes.model.AppNote
import ru.macdroid.notes.utils.REPOSITORY

class AddNewNoteViewModel(application: Application): AndroidViewModel(application) {

    fun insert(note: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {
                onSuccess()
            }
        }
    }
}