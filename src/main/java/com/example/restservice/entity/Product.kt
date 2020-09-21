package com.example.restservice.entity

data class Product(
        val id: Int = 1,
        var article: String = "default",
        var title : String = "default",
        var amount: Int = 1,
        var waybill: Waybill = Waybill(1)
)