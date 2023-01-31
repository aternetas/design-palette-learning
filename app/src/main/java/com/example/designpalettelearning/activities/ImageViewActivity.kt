package com.example.designpalettelearning.activities

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.ImageViewActivityBinding

class ImageViewActivity : MyAppCompatActivity("ImageView") {
    lateinit var binding: ImageViewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImageViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView3.setImageResource(R.drawable.ic_launcher_foreground)

        with(binding.imageView3.layoutParams){
            this.width = resources.getDimensionPixelSize(R.dimen.image_view_width)
            this.height = resources.getDimensionPixelSize(R.dimen.image_view_heigth)
        }

        binding.imageView3.requestLayout()
        Glide.with(this)
            .load("https://source.unsplash.com/random/200x200")
            .into(binding.imageView3)
    }
}