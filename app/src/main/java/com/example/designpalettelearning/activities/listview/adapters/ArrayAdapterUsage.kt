package com.example.designpalettelearning.activities.listview.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.ArrayAdapterUsageBinding
import com.example.designpalettelearning.databinding.DialogAddPersonBinding
import java.util.*

class ArrayAdapterUsage : MyAppCompatActivity("ArrayAdapterUsage") {
    private lateinit var binding: ArrayAdapterUsageBinding
    private lateinit var adapter: ArrayAdapter<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArrayAdapterUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListView()
    }

    private fun setupListView() {
        val data = mutableListOf(
            Person(
                UUID.randomUUID().toString(),
                "Shashi Snell"
            ),
            Person(
                UUID.randomUUID().toString(),
                "Itoro Black"
            ),
            Person(
                UUID.randomUUID().toString(),
                "Oghenero Albert"
            ),
            Person(
                UUID.randomUUID().toString(),
                "Khorshid Donovan"
            ),
            Person(
                UUID.randomUUID().toString(),
                "Terry Trudeau"
            )
        )

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            data
        )
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            adapter.getItem(position)?.let { deletePerson(it) }
        }

        binding.addPersonFAB.setOnClickListener { onAddPressed() }
    }

    private fun onAddPressed() {
        val dialogBinding = DialogAddPersonBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.create_user)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.add) { dialog, which ->
                val name = dialogBinding.personNameET.text.toString()
                if (name.isNotBlank()) {
                    createPerson(name)
                }
            }
            .create()
        dialog.show()
    }

    private fun createPerson(name: String) {
        val person = Person(
            UUID.randomUUID().toString(),
            name
        )
        adapter.add(person)
    }

    private fun deletePerson(person: Person) {
        val listener = DialogInterface.OnClickListener { _, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                adapter.remove(person)
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.delete_user)
            .setMessage(resources.getString(R.string.delete_user_message, person))
            .setPositiveButton(R.string.delete, listener)
            .setNegativeButton(R.string.cancel, listener)
            .create()
        dialog.show()
    }

    class Person(val id: String, val name: String) {
        override fun toString(): String = name
    }
}