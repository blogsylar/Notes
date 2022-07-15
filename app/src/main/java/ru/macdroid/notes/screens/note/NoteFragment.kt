package ru.macdroid.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.macdroid.notes.R
import ru.macdroid.notes.databinding.FragmentNoteBinding
import ru.macdroid.notes.model.AppNote
import ru.macdroid.notes.utils.APP_ACTIVITY

class NoteFragment : Fragment() {

    private var binding: FragmentNoteBinding? = null
    private val view get() = binding!!
    private lateinit var viewModel: NoteFragmentViewModel
    private lateinit var currentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as AppNote

        return view.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        view.noteName.text = currentNote.name
        view.noteText.text = currentNote.text
        viewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                viewModel.delete(currentNote) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}