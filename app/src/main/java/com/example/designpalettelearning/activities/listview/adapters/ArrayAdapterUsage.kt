package com.example.designpalettelearning.activities.listview.adapters

import android.os.Bundle
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.ArrayAdapterUsageBinding

class ArrayAdapterUsage : MyAppCompatActivity("ArrayAdapterUsage") {
    private lateinit var binding: ArrayAdapterUsageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArrayAdapterUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}