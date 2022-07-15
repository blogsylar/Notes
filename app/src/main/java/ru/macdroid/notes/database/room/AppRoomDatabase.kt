package ru.macdroid.notes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.macdroid.notes.model.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun getAppRoomDao(): AppRoomDao

    companion object {

        @Volatile // база не должна кешироваться, чтобы получать актуальные значения
        private var database: AppRoomDatabase? = null

        @Synchronized // запрещаем обращаться несколько экземпляров одновременно
        fun getInstance(context: Context): AppRoomDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                database as AppRoomDatabase
            } else database as AppRoomDatabase
        }
    }
}