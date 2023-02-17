package com.example.designpalettelearning.activities

import android.os.Bundle
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.ListViewActivityBinding

class ListViewActivity : MyAppCompatActivity("ListView") {
    private lateinit var binding: ListViewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}