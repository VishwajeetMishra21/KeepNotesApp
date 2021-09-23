package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesAdapter {
    lateinit var recyclerView: RecyclerView
    lateinit var input : EditText
    lateinit var addButton : Button
    lateinit var viewModel: NoteViewModel
    lateinit var adapter : NoteAdapter
    lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val text = input.text.toString()
            if(text.isNotEmpty()) {
                viewModel.insertNode(Note(text))
            }
        }

        layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = NoteAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {lists->
            lists?.let {
                adapter.updateList(it)
            }

        })

    }

    override fun onClicked(note: Note) {
        viewModel.deleteNode(note)
    }
}