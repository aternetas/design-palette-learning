package com.example.designpalettelearning.activities.recyclerview

import android.os.Bundle
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.RecyclerViewActivityBinding

class RecyclerViewActivity : MyAppCompatActivity("Recycler View") {
    private lateinit var binding: RecyclerViewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}