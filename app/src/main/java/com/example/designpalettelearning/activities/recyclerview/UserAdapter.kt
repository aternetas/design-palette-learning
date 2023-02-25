package com.example.designpalettelearning.activities.recyclerview

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.recyclerview.models.User
import com.example.designpalettelearning.databinding.ItemUserForRecyclerViewBinding

interface UserActionsListener {
    fun onUserMove(user: User, moveBy: Int)
    fun onUserInfo(user: User)
    fun onUserDelete(user: User)
    fun onUserFire(user: User)
}

class UserDiffUtils(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

class UserAdapter(
    private val actionListener: UserActionsListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), View.OnClickListener {
    var users: List<User> = emptyList()
        set(newValue) {
            val diffUtils = UserDiffUtils(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffUtils)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
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
        val context = holder.itemView.context
        with(holder.binding) {
            holder.itemView.tag = user
            moreActionsIV.tag = user
            nameTV.text = user.name
            companyTV.text = user.company.ifBlank { context.getString(R.string.unemployed) }
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
            if (user.company.isNotBlank()) {
                add(0, FIRE, Menu.NONE, context.getString(R.string.fire))
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
                FIRE -> {
                    actionListener.onUserFire(user)
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
        private const val FIRE = 3
        private const val DELETE = 4
    }
}