package com.example.restservice.entity

enum class Roles {
    Driver, Operator, Administrator;

    fun getPosition(role: Roles): Int {
        return when (role) {
            Driver -> 1
            Operator -> 2
            Administrator -> 3
            else ->1
        }
    }
}