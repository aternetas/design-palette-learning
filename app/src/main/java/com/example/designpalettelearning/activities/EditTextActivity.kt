package com.example.designpalettelearning.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.designpalettelearning.activities.constants.InputType
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.EditTextActivityBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class EditTextActivity : MyAppCompatActivity("EditText") {
    private lateinit var binding: EditTextActivityBinding
    private var inputType = InputType.NONE
        set(value) {
            field = value
        }
    private var keyword = ""
        set(value) {
            encodedKeyword = URLEncoder.encode(value, StandardCharsets.UTF_8.name())
            field = value
        }
    private lateinit var encodedKeyword: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditTextActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createActions()
        updateUi()
    }

    private fun createActions() {
        binding.getRandomImageButton2.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.showAdvancedSettingsSwitch.setOnCheckedChangeListener { _, _ ->
            if (!binding.showAdvancedSettingsSwitch.isChecked) {
                inputType = InputType.NONE
            }
            updateUi()
        }

        binding.selectAKeywordCheckBox.setOnClickListener {
            inputType = if (binding.selectAKeywordCheckBox.isChecked) InputType.SELECT
            else InputType.NONE

            updateUi()
        }

        binding.enterAKeywordCheckBox.setOnClickListener {
            inputType = if (binding.enterAKeywordCheckBox.isChecked) InputType.ENTER
            else InputType.NONE

            updateUi()
        }

        binding.keywordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onGetRandomImagePressed()
                if (keyword.isBlank()) {
                    binding.keywordEditText.error = "Keyword is empty"
                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }
    }

    private fun onGetRandomImagePressed() {
        when (inputType) {
            InputType.SELECT -> {
                val checkId = binding.keywordsRadioGroup.checkedRadioButtonId
                keyword = binding.keywordsRadioGroup.findViewById<RadioButton>(checkId).text.toString()
            }

            InputType.ENTER -> {
                keyword = binding.keywordEditText.text.toString()
                if (keyword.isBlank()) {
                    binding.keywordEditText.error = "Keyword is empty"
                    return
                }
            }

            InputType.NONE -> {
                keyword = ""
            }
        }

        uploadAndSetImage()
    }

    private fun uploadAndSetImage() {
        binding.progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?$encodedKeyword")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }
            })
//for background image during loading
//          .placeholder(R.drawable.image_downloading)
            .into(binding.imageView)
    }

    private fun updateUi() = with(binding) {
        checkBoxesCL.isVisible = showAdvancedSettingsSwitch.isChecked

        when (inputType) {
            InputType.SELECT -> {
                enterAKeywordCheckBox.isChecked = false
                keywordEditText.isVisible = false
                keywordsRadioGroup.isVisible = true
            }

            InputType.ENTER -> {
                selectAKeywordCheckBox.isChecked = false
                keywordsRadioGroup.isVisible = false
                keywordEditText.isVisible = true
            }

            InputType.NONE -> {
                keywordsRadioGroup.isVisible = false
                keywordEditText.isVisible = false
                selectAKeywordCheckBox.isChecked = false
                enterAKeywordCheckBox.isChecked = false
            }
        }
    }
}