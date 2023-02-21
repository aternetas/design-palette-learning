package com.example.designpalettelearning.activities.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.recyclerview.models.User
import com.example.designpalettelearning.databinding.ItemUserForRecyclerViewBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserForRecyclerViewBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            nameTV.text = user.name
            companyTV.text = user.company
            if (user.photo.isNotBlank()) {
                Glide.with(avatarIV.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(avatarIV)
            } else {
                avatarIV.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(val binding: ItemUserForRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)
}