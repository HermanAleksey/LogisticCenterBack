package com.example.restservice.entity

data class Account(
        val id: Int = 0,
        var login: String = "default",
        var password: String = "default",
        var role: Roles = Roles.Driver) {
}