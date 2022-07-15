package ru.macdroid.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.macdroid.notes.R
import ru.macdroid.notes.databinding.NoteItemBinding
import ru.macdroid.notes.model.AppNote

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var listNotes = emptyList<AppNote>()

    class MainViewHolder(view: NoteItemBinding): RecyclerView.ViewHolder(view.root) {
        val nameNote: TextView = view.itemNoteName
        val textNote: TextView = view.itemNoteText
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.nameNote.text = listNotes[position].name
        holder.textNote.text = listNotes[position].text
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    fun setList(list: List<AppNote>) {
        listNotes = list
        notifyDataSetChanged()
    }
}