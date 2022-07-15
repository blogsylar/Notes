package ru.macdroid.notes.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.macdroid.notes.model.AppNote
import ru.macdroid.notes.utils.REPOSITORY

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun delete(note: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note) {
                onSuccess()
            }
        }
    }
}