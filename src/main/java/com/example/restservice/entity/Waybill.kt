package com.example.restservice.entity

data class Waybill(
        var id: Int,
//        var product: Product,
        var dateOfDelivery: String,
        var dateOfShipment: String,
        var driver: Driver,
        var operator: Operator) {
}