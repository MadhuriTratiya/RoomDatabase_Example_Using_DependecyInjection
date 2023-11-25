package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diroomdatabase.R
import com.example.diroomdatabase.databinding.FragmentAddNoteBinding
import com.example.diroomdatabase.db.NoteEntity
import com.example.diroomdatabase.viewmodel.DatabaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


class AddNoteFragment : BottomSheetDialogFragment() {

    private var _binding :FragmentAddNoteBinding?=null
    private val binding get() = _binding

    private val noteEntity by lazy { NoteEntity()}
    private val viewModel: DatabaseViewModel by inject()

    private var noteTitle = ""
    private var notedesc = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.state = STATE_EXPANDED
        binding!!.apply {
            imgClose.setOnClickListener {
                dismiss()
            }
            btnSave.setOnClickListener {
                noteTitle = edtTitle.text.toString()
                notedesc = edtDesc.text.toString()

                if(noteTitle.isEmpty() || notedesc.isEmpty()){
                    Snackbar.make(it,"Title and Description Cannot be Empty!",Snackbar.LENGTH_LONG).show()
                }else{
                    noteEntity.noteId=0
                    noteEntity.noteTitle = noteTitle
                    noteEntity.notDesc=notedesc

                    viewModel.saveNote(noteEntity)

                    edtTitle.setText("")
                    edtDesc.setText("")

                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}