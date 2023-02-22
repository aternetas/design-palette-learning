package com.example.designpalettelearning.activities.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.recyclerview.models.User
import com.example.designpalettelearning.databinding.ItemUserForRecyclerViewBinding

interface UserActionsListener {
    fun onUserMove(user: User, moveBy: Int)
    fun onUserInfo(user: User)
    fun onUserDelete(user: User)
}

class UserAdapter(
    private val actionListener: UserActionsListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), View.OnClickListener {
    var users: List<User> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserForRecyclerViewBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.moreActionsIV.setOnClickListener(this)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            holder.itemView.tag = user
            moreActionsIV.tag = user
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
                Glide.with(avatarIV.context)
                    .clear(avatarIV)
                avatarIV.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    override fun onClick(v: View) {
        val user = v.tag as User
        when (v.id) {
            R.id.moreActionsIV -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onUserInfo(user)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val user = view.tag as User
        val position = users.indexOfFirst { it.id == user.id }
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context

        with(popupMenu.menu) {
            add(0, MOVE_UP, Menu.NONE, context.getString(R.string.move_up)).apply {
                isEnabled = position > 0
            }
            add(0, MOVE_DOWN, Menu.NONE, context.getString(R.string.move_down)).apply {
                isEnabled = position < users.size - 1
            }
            add(0, DELETE, Menu.NONE, context.getString(R.string.delete))
        }

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                MOVE_UP -> {
                    actionListener.onUserMove(user, -1)
                }
                MOVE_DOWN -> {
                    actionListener.onUserMove(user, 1)
                }
                DELETE -> {
                    actionListener.onUserDelete(user)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    class UserViewHolder(val binding: ItemUserForRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val MOVE_UP = 1
        private const val MOVE_DOWN = 2
        private const val DELETE = 3
    }
}