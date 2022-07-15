package ru.macdroid.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.macdroid.notes.databinding.ActivityMainBinding
import ru.macdroid.notes.utils.APP_ACTIVITY
import ru.macdroid.notes.utils.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        APP_ACTIVITY = this

        toolbar = view.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setSupportActionBar(toolbar)
        title = getString(R.string.title)

        AppPreference.getPreferences(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}