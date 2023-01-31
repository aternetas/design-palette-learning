package com.example.designpalettelearning.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.ButtonActivityBinding

class ButtonActivity : MyAppCompatActivity("Button") {
    lateinit var binding: ButtonActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ButtonActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getRandomImageButton.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.getRandomImageButton.setOnLongClickListener {
            onGetRandomImageLongPressed()
        }
    }

    fun onGetRandomImagePressed(){
        binding.randomImageView.setBackgroundColor(Color.WHITE)
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.image_downloading)
            .into(binding.randomImageView)
    }

    fun onGetRandomImageLongPressed(): Boolean {
        Toast.makeText(this, "long click", Toast.LENGTH_LONG).show()
        return true
    }
}