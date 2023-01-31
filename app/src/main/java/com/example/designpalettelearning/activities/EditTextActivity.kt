package com.example.designpalettelearning.activities

import android.os.Bundle
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.EditTextActivityBinding

class EditTextActivity : MyAppCompatActivity("EditText") {
    lateinit var binding: EditTextActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditTextActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}