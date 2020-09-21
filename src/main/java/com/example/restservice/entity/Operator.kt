package com.example.restservice.entity

data class Operator(
        var id: Int = 1,
        var FIO: String = "Default",
        var phoneNumber: String = "Default",
        var account: Account = Account(1)) {
}