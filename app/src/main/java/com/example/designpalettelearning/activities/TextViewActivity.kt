package com.example.designpalettelearning.activities

import android.os.Bundle
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity

class TextViewActivity : MyAppCompatActivity("TextView") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_view_activity)
    }
}