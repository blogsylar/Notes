package ru.macdroid.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.macdroid.notes.R
import ru.macdroid.notes.databinding.FragmentStartBinding
import ru.macdroid.notes.utils.*

class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val view get() = binding!!
    private lateinit var viewModel: StartFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        if (AppPreference.getinitUser()) {
            viewModel.initDB(AppPreference.getTypeDB()) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else {
            initialization()
        }
    }

    private fun initialization() {

        view.btnRoom.setOnClickListener {
           viewModel.initDB(TYPE_ROOM) {
               AppPreference.setInitUser(true)
               AppPreference.setTypeDB(TYPE_ROOM)
               APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
           }
        }

        view.btnFirebase.setOnClickListener {
            view.inputEmail.visibility = View.VISIBLE
            view.inputPassword.visibility = View.VISIBLE
            view.btnLogin.visibility = View.VISIBLE
            view.btnLogin.setOnClickListener {
                val inputEmail = view.inputEmail.text.toString()
                val inputPassword = view.inputPassword.text.toString()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    viewModel.initDB(TYPE_FIREBASE) {
                        AppPreference.setInitUser(true)
                        AppPreference.setTypeDB(TYPE_FIREBASE)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else {
                    showToast(getString(R.string.empty_fields_text))
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}