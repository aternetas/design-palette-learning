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
    private lateinit var binding: EditTextActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditTextActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getRandomImageButton2.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.showAdvancedSettingsSwitch.setOnCheckedChangeListener { _, _ ->
            updateUi()
        }

        binding.selectAKeywordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            updateUi()
        }

        binding.printAKeywordCheckBox.setOnCheckedChangeListener { _, _ ->
            updateUi()
        }

        binding.printAKeywordCheckBox.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener onGetRandomImagePressed()
            }
            return@setOnEditorActionListener false
        }

        updateUi()
    }

    fun onGetRandomImagePressed(): Boolean {
        val keyword = binding.keywordEditText.text.toString()
        if (binding.printAKeywordCheckBox.isChecked && keyword.isBlank()) {
            binding.keywordEditText.error = "Keyword is empty"
            return true
        }

        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600 ${if(binding.printAKeywordCheckBox.isChecked) "?$encodedKeyword" else "?"}")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.image_downloading)
            .into(binding.imageView)

        return false
    }

    private fun updateUi() = with(binding) {
        if (showAdvancedSettingsSwitch.isChecked){
            selectAKeywordCheckBox.visibility = View.VISIBLE
            printAKeywordCheckBox.visibility = View.VISIBLE

            if (selectAKeywordCheckBox.isChecked) {
                keywordsRadioGroup.visibility= View.VISIBLE
            } else {
                keywordsRadioGroup.visibility= View.GONE
            }

            if (printAKeywordCheckBox.isChecked) {
                keywordEditText.visibility = View.VISIBLE
            } else {
                keywordEditText.visibility = View.GONE
            }

        } else {
            printAKeywordCheckBox.visibility = View.GONE
            printAKeywordCheckBox.visibility = View.GONE
        }
    }
}