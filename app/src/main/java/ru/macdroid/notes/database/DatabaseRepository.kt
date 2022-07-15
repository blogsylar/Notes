package ru.macdroid.notes.database

import androidx.lifecycle.LiveData
import ru.macdroid.notes.model.AppNote

interface DatabaseRepository {
    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess: () -> Unit)
    suspend fun delete(note: AppNote, onSuccess: () -> Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFailed: (String) -> Unit) {}

    fun signOut() {}
}