package com.example.designpalettelearning.activities.recyclerview

import android.app.Application
import com.example.designpalettelearning.activities.recyclerview.services.UserService

class App : Application() {
    val userService = UserService()
}