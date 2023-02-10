package com.example.designpalettelearning.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.MainScreenActivityBinding

class MainScreenActivity : MyAppCompatActivity("MainScreen") {
    lateinit var binding: MainScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.designpalettelearning.databinding.MainScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onTextViewPressed(view: View) {
        binding.buttonTextView.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            startActivity(intent)
        }
    }

    fun onImageViewPressed(view: View) {
        binding.buttonImageView.setOnClickListener {
            val intent = Intent(this, ImageViewActivity::class.java)
            startActivity(intent)
        }
    }

    fun onButtonPressed(view: View) {
        binding.buttonButton.setOnClickListener {
            val intent = Intent(this, ButtonActivity::class.java)
            startActivity(intent)
        }
    }

    fun onEditTextPressed(view: View) {
        binding.buttonEditText.setOnClickListener {
            val intent = Intent(this, EditTextActivity::class.java)
            startActivity(intent)
        }
    }
}