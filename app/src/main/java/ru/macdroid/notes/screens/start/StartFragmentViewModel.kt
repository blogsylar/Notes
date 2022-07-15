package ru.macdroid.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.macdroid.notes.database.firebase.AppFirebaseRepository
import ru.macdroid.notes.database.room.AppRoomDatabase
import ru.macdroid.notes.database.room.AppRoomRepository
import ru.macdroid.notes.utils.REPOSITORY
import ru.macdroid.notes.utils.TYPE_FIREBASE
import ru.macdroid.notes.utils.TYPE_ROOM
import ru.macdroid.notes.utils.showToast

class StartFragmentViewModel (application: Application) : AndroidViewModel(application) {
    private val context = application

    fun initDB(type: String, onSuccess:() -> Unit) {
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({ onSuccess() }, { showToast(it) })
            }
        }
    }
}