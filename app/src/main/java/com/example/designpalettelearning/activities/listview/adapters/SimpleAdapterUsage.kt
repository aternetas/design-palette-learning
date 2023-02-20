package com.example.designpalettelearning.activities.listview.adapters

import android.app.AlertDialog
import android.os.Bundle
import android.widget.AdapterView
import android.widget.SimpleAdapter
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.SimpleAdapterUsageBinding

private const val KEY_TITLE = "Name"
private const val KEY_DESCRIPTION = "Phone"
class SimpleAdapterUsage : MyAppCompatActivity("SimpleAdapterUsage") {
    private lateinit var binding: SimpleAdapterUsageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SimpleAdapterUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListView()
    }

    private fun setupListView() {
        val data = listOf(
            mapOf(
                KEY_TITLE to "Chimwala Zini",
                KEY_DESCRIPTION to "+1 202-918-2132"
            ),
            mapOf(
                KEY_TITLE to "Kit Teke",
                KEY_DESCRIPTION to "+1 264-235-1021"
            ),
            mapOf(
                KEY_TITLE to "Yolotzin Noel",
                KEY_DESCRIPTION to "+56 72-262-9742"
            ),
            mapOf(
                KEY_TITLE to "Mahinder Sharma",
                KEY_DESCRIPTION to "+1 473-901-5624"
            ),
            mapOf(
                KEY_TITLE to "Aanakwad Ignatov",
                KEY_DESCRIPTION to "+257 77-80-84-64"
            )
        )

        val adapter = SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItemTitle = data[position][KEY_TITLE]
            val selectedItemDescription = data[position][KEY_DESCRIPTION]

            val dialog = AlertDialog.Builder(this)
                .setTitle(selectedItemTitle)
                .setMessage(selectedItemDescription)
                .setPositiveButton("Ok") { _, _ -> }
                .setNegativeButton("No") { _, _ -> }
                .create()

            dialog.show()
        }
    }
}