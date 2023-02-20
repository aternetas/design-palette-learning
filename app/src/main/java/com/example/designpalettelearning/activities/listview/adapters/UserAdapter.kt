package com.example.designpalettelearning.activities.listview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.designpalettelearning.activities.listview.User
import com.example.designpalettelearning.databinding.ItemUserBinding

typealias onDeleteUserListener = (User) -> Unit

class UserAdapter(
    private val users: List<User>,
    private val onDeleteUserListener: onDeleteUserListener
) : BaseAdapter(), View.OnClickListener {

    override fun getCount(): Int {
        return users.size
    }

    override fun getItem(position: Int): User {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return users[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = convertView?.tag as ItemUserBinding? ?: createBinding(parent.context)
        val user = getItem(position)
        binding.userTV.text = user.name
        binding.deleteUserIV.tag = user

        return binding.root
    }

    override fun onClick(v: View) {
        val user = v.tag as User
        onDeleteUserListener.invoke(user)
    }

    private fun createBinding(context: Context): ItemUserBinding {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context))
        binding.deleteUserIV.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }
}