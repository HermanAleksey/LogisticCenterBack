package com.example.restservice.entity

data class Account(
        val id: Int = 0,
        var login: String,
        var password: String,
        var role: Roles) {
}