package com.example.restservice.entity

data class Product(
        val id: Int,
        var article: String,
        var title : String,
        var amount: Int,
        var waybill: Waybill
) {
}