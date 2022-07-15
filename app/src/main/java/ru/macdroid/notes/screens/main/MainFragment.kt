package ru.macdroid.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.macdroid.notes.R
import ru.macdroid.notes.databinding.FragmentMainBinding
import ru.macdroid.notes.model.AppNote
import ru.macdroid.notes.utils.APP_ACTIVITY
import ru.macdroid.notes.utils.AppPreference

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val view get() = binding!!
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var observerList: Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return view.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        adapter = MainAdapter()
        recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        observerList = Observer {
            val list = it.asReversed()
            adapter.setList(list)
        }

        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.allNotes.observe(this, observerList)
        view.btnAddNote.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                viewModel.signOut()
                AppPreference.setInitUser(false)
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewModel.allNotes.removeObserver(observerList)
        recyclerView.adapter = null
    }

    companion object { //чтобы адаптер не плодил экземпляры MainAdapter
        fun click(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }
}