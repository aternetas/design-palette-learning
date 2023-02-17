package com.example.designpalettelearning.activities.listview

import android.content.Intent
import android.os.Bundle
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.activities.listview.adapters.ArrayAdapterUsage
import com.example.designpalettelearning.activities.listview.adapters.SimpleAdapterUsage
import com.example.designpalettelearning.databinding.ListViewActivityBinding

class ListViewActivity : MyAppCompatActivity("ListView") {
    lateinit var binding: ListViewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createActions()
    }

    private fun createActions() {
        binding.buttonSimpleAdapter.setOnClickListener {
            val intent = Intent(this, SimpleAdapterUsage::class.java)
            startActivity(intent)
        }

        binding.buttonArrayAdapter.setOnClickListener {
            val intent = Intent(this, ArrayAdapterUsage::class.java)
            startActivity(intent)
        }
    }
}