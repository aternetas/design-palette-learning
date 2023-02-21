package com.example.designpalettelearning.activities

import android.os.Bundle
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.TextViewActivityBinding

class TextViewActivity : MyAppCompatActivity("TextView") {
    lateinit var binding: TextViewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TextViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.textView2){
            setBackgroundResource(R.color.text_view_background)
            setLineSpacing(0F, 3F)
        }
    }
}