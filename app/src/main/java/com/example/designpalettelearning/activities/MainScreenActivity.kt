package com.example.designpalettelearning.activities

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.activities.listview.ListViewActivity
import com.example.designpalettelearning.databinding.MainScreenActivityBinding

class MainScreenActivity : MyAppCompatActivity("MainScreen") {
    lateinit var binding: MainScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createActions()
    }

    private fun createActions() {
        binding.buttonTextView.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            showProgressBar { if (it) { startActivity(intent) } }
        }

        binding.buttonImageView.setOnClickListener {
            val intent = Intent(this, ImageViewActivity::class.java)
            showProgressBar { if (it) { startActivity(intent) } }
        }

        binding.buttonButton.setOnClickListener {
            val intent = Intent(this, ButtonActivity::class.java)
            showProgressBar { if (it) { startActivity(intent) } }

        }

        binding.buttonEditText.setOnClickListener {
            val intent = Intent(this, EditTextActivity::class.java)
            showProgressBar { if (it) { startActivity(intent) } }
        }

        binding.buttonListView.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            showProgressBar { if (it) { startActivity(intent) } }
        }
    }

    private fun showProgressBar(compl: (Boolean) -> Unit) {
        val finishProgress = 1000
        with(binding.progressBarHorizontal) {
            this.max = finishProgress
            this.isVisible = true

            val animator = ObjectAnimator
                .ofInt(this, "progress", finishProgress)
                .setDuration(2000)

            animator.doOnEnd {
                this.isVisible = false
                this.progress = 0
                compl(true)
            }
            animator.start()
        }
    }
}