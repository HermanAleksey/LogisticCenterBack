package com.example.restservice.entity

data class CenterProduct(
        var id: Int,
        var center: LogisticsCenter,
        var product: Product
) {
}