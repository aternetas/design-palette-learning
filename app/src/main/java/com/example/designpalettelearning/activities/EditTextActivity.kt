package com.example.designpalettelearning.activities

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.EditTextActivityBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class EditTextActivity : MyAppCompatActivity("EditText") {
    lateinit var binding: EditTextActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditTextActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getRandomImageButton2.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.showCheckboxSwitch.setOnCheckedChangeListener { _, _ ->
            updateUi()
        }

        binding.keywordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener onGetRandomImagePressed()
            }
            return@setOnEditorActionListener false
        }

        binding.useKeywordCheckBox.setOnCheckedChangeListener { _, _ ->
            updateUi()
        }

        updateUi()
    }

    fun onGetRandomImagePressed(): Boolean {
        val keyword = binding.keywordEditText.text.toString()
        if (binding.useKeywordCheckBox.isChecked && keyword.isBlank()) {
            binding.keywordEditText.error = "Keyword is empty"
            return true
        }

        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600 ${if(binding.useKeywordCheckBox.isChecked) "?$encodedKeyword" else "?"}")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.image_downloading)
            .into(binding.imageView)

        return false
    }

    private fun updateUi() = with(binding) {
        if (showCheckboxSwitch.isChecked){
            useKeywordCheckBox.visibility = View.VISIBLE

            if (useKeywordCheckBox.isChecked) {
                keywordEditText.visibility = View.VISIBLE
            } else {
                keywordEditText.visibility = View.GONE
            }
        } else {
            useKeywordCheckBox.visibility = View.GONE
        }
    }
}