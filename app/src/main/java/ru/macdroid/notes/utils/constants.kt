package ru.macdroid.notes.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ru.macdroid.notes.MainActivity
import ru.macdroid.notes.database.DatabaseRepository

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID: String
lateinit var REF_DATABASE: DatabaseReference
lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
lateinit var EMAIL: String
lateinit var PASSWORD: String
const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"