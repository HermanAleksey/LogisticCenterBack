package com.example.restservice.entity

data class Operator(
        var id: Int = 0,
        var FIO: String,
        var phoneNumber: String,
        var account: Account) {
}