package com.example.designpalettelearning.activities.extensions

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class MyAppCompatActivity(_activityName: String) : AppCompatActivity() {
    var activityName: String

    init {
        activityName = "Log: $_activityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(activityName, "onCreate called")
    }

    override fun onStart() {
        super.onStart()

        Log.d(activityName, "onStart called")
    }

    override fun onResume() {
        super.onResume()

        Log.d(activityName, "onResume called")
    }

    override fun onPause() {
        super.onPause()

        Log.d(activityName, "onPause called")
    }

    override fun onStop() {
        super.onStop()

        Log.d(activityName, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(activityName, "onDestroy called")
    }
}