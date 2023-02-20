package com.example.designpalettelearning.activities.listview.adapters

import android.os.Bundle
import com.example.designpalettelearning.R
import com.example.designpalettelearning.activities.extensions.MyAppCompatActivity
import com.example.designpalettelearning.databinding.BaseAdapterUsageBinding

class BaseAdapterUsage : MyAppCompatActivity("BaseAdapter") {
    lateinit var binding: BaseAdapterUsageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseAdapterUsageBinding.inflate(layoutInflater)
        setContentView(R.layout.base_adapter_usage)
    }
}