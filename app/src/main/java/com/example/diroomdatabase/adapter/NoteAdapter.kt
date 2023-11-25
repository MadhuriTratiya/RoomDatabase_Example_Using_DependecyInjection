package com.example.diroomdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diroomdatabase.databinding.LayoutNoteBinding
import com.example.diroomdatabase.db.NoteEntity


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var binding: LayoutNoteBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = LayoutNoteBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root){
        fun bind(noteEntity: NoteEntity){
            binding.apply {
                tvtitle.text = noteEntity.noteTitle
                tvdisc.text = noteEntity.notDesc
            }
        }
    }

    private val differCallack = object : DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallack)
}