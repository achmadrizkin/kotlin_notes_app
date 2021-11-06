package com.example.kotlin_notes_app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_notes_app.database.NotesDatabase
import com.example.kotlin_notes_app.entities.Notes
import com.example.kotlin_notes_app.util.NoteBottomSheetFragment
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.util.*


class CreateNoteFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var currentDate: String? = null
    private var selectedColor = "#171C26"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            BroadcastReceiver, IntentFilter("bottom_sheet_action")
        )

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())

        colorView.setBackgroundColor(Color.parseColor(selectedColor))
        colorView2.setBackgroundColor(Color.parseColor(selectedColor))
        tvDateTime.text = currentDate

        //
        imgDone.setOnClickListener {
            saveNote()
        }

        imgBack.setOnClickListener {
//            replaceFragment(HomeFragment.newInstance(), false)
                requireActivity().supportFragmentManager.popBackStack()
        }

        //
        imgMore.setOnClickListener {
            var noteBottomSheetFragment = NoteBottomSheetFragment.newInstance()
            noteBottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                "Note Bottom Sheet"
            )
        }
    }

    private fun saveNote() {
        if (etNoteTitle.text.isNullOrEmpty()) {
            Toast.makeText(context, "Note Title is Required", Toast.LENGTH_SHORT).show()
        } else if (etNoteSubTitle.text.isNullOrEmpty()) {

            Toast.makeText(context, "Note Sub Title is Required", Toast.LENGTH_SHORT).show()
        } else if (etNoteDescription.text.isNullOrEmpty()) {

            Toast.makeText(context, "Note Description is Required", Toast.LENGTH_SHORT).show()
        }

        launch {
            val notes = Notes()

            notes.title = etNoteTitle.text.toString()
            notes.subTitle = etNoteSubTitle.text.toString()
            notes.noteText = etNoteDescription.text.toString()
            notes.dateTime = currentDate
            notes.color = selectedColor

            context?.let {
                NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                replaceFragment(HomeFragment.newInstance(), false)
                Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show()

                etNoteTitle.setText("")
                etNoteSubTitle.setText("")
                etNoteDescription.setText("")
            }
        }

    }

    fun replaceFragment(fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.add(R.id.frameLayout, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }

    private val BroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            var actionColor = p1!!.getStringExtra("actionColor")

            when (actionColor) {
                "Blue" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }

                "Yellow" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }

                "Purple" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }

                "Green" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }

                "Orange" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }

                "Black" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }

                else -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                    colorView2.setBackgroundColor(Color.parseColor(selectedColor))
                }
            }
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(BroadcastReceiver)
        super.onDestroy()
    }
}