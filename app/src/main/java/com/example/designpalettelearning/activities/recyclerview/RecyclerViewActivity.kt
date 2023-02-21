package com.example.designpalettelearning.activities.recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.activities.recyclerview.services.UserListener
import com.example.designpalettelearning.activities.recyclerview.services.UserService
import com.example.designpalettelearning.databinding.RecyclerViewActivityBinding

class RecyclerViewActivity : MyAppCompatActivity("Recycler View") {
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

        adapter = UserAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter

        userService.addListener(userListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        userService.removeListener(userListener)
    }
}