package com.example.designpalettelearning.activities.recyclerview

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.activities.recyclerview.models.User
import com.example.designpalettelearning.activities.recyclerview.services.UserListener
import com.example.designpalettelearning.activities.recyclerview.services.UserService
import com.example.designpalettelearning.databinding.RecyclerViewActivityBinding

class RecyclerViewActivity : MyAppCompatActivity("Recycler View"), UserActionsListener {
    private lateinit var binding: RecyclerViewActivityBinding
    private lateinit var adapter: UserAdapter

    private val userService: UserService
        get() = (applicationContext as App).userService

    private val userListener: UserListener = {
        adapter.users = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this)

        val layoutManager = LinearLayoutManager(this)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
        val itemAnimator = binding.rV.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }

        userService.addListener(userListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        userService.removeListener(userListener)
    }

    /*UserActionsListener*/
    override fun onUserMove(user: User, moveBy: Int) {
        userService.moveUser(user, moveBy)
    }

    override fun onUserInfo(user: User) {
        Toast.makeText(this@RecyclerViewActivity, "User: ${user.name}", Toast.LENGTH_LONG).show()
    }

    override fun onUserFire(user: User) {
        userService.fireUser(user)
    }

    override fun onUserDelete(user: User) {
        val dialog = AlertDialog.Builder(this@RecyclerViewActivity)
            .setTitle(R.string.delete_user)
            .setMessage(resources.getString(R.string.delete_user_message, user.name))
            .setPositiveButton(R.string.ok) {_, which ->
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    userService.deleteUser(user)
                }
            }
            .setNegativeButton(R.string.cancel) { _, _ ->}
            .create()
        dialog.show()
    }
}