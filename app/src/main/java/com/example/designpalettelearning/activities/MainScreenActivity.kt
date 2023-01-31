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
        binding = MainScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onTextView(view: View){
        binding.buttonTextView.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            startActivity(intent)
        }
    }

    fun onImageView(view: View){
        binding.buttonImageView.setOnClickListener {
            val intent = Intent(this, ImageViewActivity::class.java)
            startActivity(intent)
        }
    }
}