package com.example.restservice.entity

data class Waybill(
        var id: Int = 1,
//        var product: Product,
        var dateOfDelivery: String = "default",
        var dateOfShipment: String = "default",
        var driver: Driver = Driver(),
        var operator: Operator = Operator()) {
}