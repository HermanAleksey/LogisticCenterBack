package com.example.restservice.controller

import com.example.restservice.entity.CenterProduct
import com.example.restservice.entity.CenterProductId
import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.service.CenterProductService
import com.example.restservice.service.CenterService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CenterProductController {

    @PutMapping("/centerproduct")
    fun insertCenterProduct(
            @RequestBody centerProduct: CenterProduct
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                CenterProductService().insertCenterProduct(centerProduct)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/centerproducts")
    fun getAllCenterProducts(): List<CenterProduct> {
        return CenterProductService().getAllCenterProduct()
    }

    @GetMapping("/centerproduct")
    fun getCenterProduct(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestParam(value = "attribute", defaultValue = "center_product") attribute: String
    ): List<CenterProduct> {
        val a = when (attribute) {
            "center_product" -> CenterProductId.CENTER_PRODUCT_ID
            "center" -> CenterProductId.CENTER_ID
            "product" -> CenterProductId.PRODUCT_ID
            else -> CenterProductId.CENTER_PRODUCT_ID
        }
        return CenterProductService().getCenterProductById(id, a)
    }

    @PatchMapping("/centerproduct")
    fun updateCenterProduct(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestBody centerProduct: CenterProduct
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                CenterProductService().updateCenterProduct(id, centerProduct)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/centerproduct")
    fun deleteCenterProduct(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestParam(value = "attribute", defaultValue = "center_product") attribute: String
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        val a = when (attribute) {
            "center_product" -> CenterProductId.CENTER_PRODUCT_ID
            "center" -> CenterProductId.CENTER_ID
            "product" -> CenterProductId.PRODUCT_ID
            else -> CenterProductId.CENTER_PRODUCT_ID
        }

        return if (
                CenterProductService().removeCenterProductById(id, a)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }
}