package com.example.designpalettelearning.activities.listview.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.activities.listview.User
import com.example.designpalettelearning.databinding.BaseAdapterUsageBinding
import kotlin.random.Random

class BaseAdapterUsage : MyAppCompatActivity("BaseAdapter") {
    private lateinit var binding: BaseAdapterUsageBinding
    private val data = mutableListOf(
        User(getRandomNumber(), "Gefen Vasilev"),
        User(getRandomNumber(), "Alaba Devlin"),
        User(getRandomNumber(), "Jaiden Rice"),
        User(getRandomNumber(), "Jinan Waterman"),
        User(getRandomNumber(), "Kahurangi Lindgren")
    )
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseAdapterUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
    }

    private fun getRandomNumber(): Long = Random.nextLong(100000, 999999)

    private fun setupList() {
        adapter = UserAdapter(data) { deleteUser(it) }

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            showUserInfo(adapter.getItem(position))
        }
    }

    private fun createUser(name: String) {
        val user = User(getRandomNumber(), name)
        data.add(user)
        adapter.notifyDataSetChanged()
    }

    private fun showUserInfo(user: User) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.info)
            .setMessage("${user.name}\nId: ${user.id}")
            .setPositiveButton(R.string.ok) { _, _ -> }
            .create()
        dialog.show()
    }

    private fun deleteUser(user: User) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                data.remove(user)
                adapter.notifyDataSetChanged()
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.delete_user)
            .setMessage(resources.getString(R.string.delete_user_message, user.name))
            .setPositiveButton(R.string.delete, listener)
            .setNegativeButton(R.string.cancel, listener)
            .create()
        dialog.show()
    }
}