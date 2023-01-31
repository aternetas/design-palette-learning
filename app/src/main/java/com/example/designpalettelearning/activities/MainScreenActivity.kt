package com.example.designpalettelearning.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.designpalettelearning.databinding.MainScreenActivityBinding

class MainScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: MainScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = MainScreenActivityBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onTextView(view: View){
        bindingClass.buttonTextView.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            startActivity(intent)
        }
    }
}