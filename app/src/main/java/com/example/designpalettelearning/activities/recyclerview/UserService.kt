package com.example.designpalettelearning.activities.recyclerview

import com.example.designpalettelearning.activities.recyclerview.models.User
import com.github.javafaker.Faker
import java.util.Collections

typealias UserListener = (users: List<User>) -> Unit

class UserService {
    private var users = mutableListOf<User>()
    private var listeners = mutableSetOf<UserListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        val generatedUsers = (1..100).map {
            User(
                it.toLong(),
                faker.funnyName().name(),
                IMAGES[it % IMAGES.size],
                faker.company().name()
            )
        }
    }

    fun getUsers(): List<User> = users

    fun deleteUser(user: User) {
        val userIndex: Int = users.indexOfFirst { it.id == user.id }
        if (userIndex != -1) {
            users.removeAt(userIndex)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex: Int = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener(listener: UserListener){
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UserListener) = listeners.remove(listener)

    private fun notifyChanges() = listeners.forEach { it.invoke(users) }

    companion object {
        private val IMAGES = mutableListOf(
            "https://images.unsplash.com/photo-1549068106-b024baf5062d?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8aHVtYW58fHx8fHwxNjc2OTkxOTcy&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1494790108377-be9c29b29330?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8YXZhdGFyfHx8fHx8MTY3Njk5MjExNg&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1579987102269-ac45dafda12c?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY3Njk5MjI1Nw&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1584997159889-8bb96d0a2217?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8aHVtYW58fHx8fHwxNjc2OTkyMTY3&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1614880353165-e56fac2ea9a8?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8aHVtYW58fHx8fHwxNjc2OTkyMTUy&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY3Njk5MjIwNw&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1612928414075-bc722ade44f1?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8aHVtYW58fHx8fHwxNjc2OTkyMTg3&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1568604032475-10f1a56527c9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8bWFufHx8fHx8MTY3Njk5MjE5NQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1596815064285-45ed8a9c0463?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8aHVtYW58fHx8fHwxNjc2OTkyMTU5&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1633549446051-82a0599e9e02?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218MHx8aHVtYW58fHx8fHwxNjc2OTkyMTQz&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800"
        )
    }
}