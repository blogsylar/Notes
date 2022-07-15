package ru.macdroid.notes.screens.add_new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.macdroid.notes.R
import ru.macdroid.notes.databinding.FragmentAddNewNoteBinding
import ru.macdroid.notes.model.AppNote
import ru.macdroid.notes.utils.APP_ACTIVITY
import ru.macdroid.notes.utils.showToast

class AddNewNoteFragment : Fragment() {

    private var binding: FragmentAddNewNoteBinding? = null
    private val view get() = binding!!
    private lateinit var viewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {

        viewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)

        view.btnAddNote.setOnClickListener {
            val name = view.inputNameNote.text.toString()
            val text = view.inputTextNote.text.toString()

            if (name.isEmpty()) {
                showToast(getString(R.string.toast_enter_empty_name))
            } else {
                viewModel.insert(AppNote(name = name, text = text)) {
                    lifecycleScope.launch(Dispatchers.Main) { // так как данные отправляли через IO поток, отображение делается в главном, явно это указываю
                        APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}