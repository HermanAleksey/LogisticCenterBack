package com.example.restservice.controller

import com.example.restservice.entity.Product
import com.example.restservice.service.ProductService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @PutMapping("/product")
    fun insertProduct(
            @RequestBody product: Product
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if(
                ProductService().insertProduct(product)
        ){
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/products")
    fun getAllProducts(): List<Product> {
        return ProductService().getProducts()
    }

    @GetMapping("/product")
    fun getCenter(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): Product {
        return ProductService().getProduct(id)
    }

    @PatchMapping("/product")
    fun updateProduct(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestBody product: Product
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                ProductService().updateProduct(id,product)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/product")
    fun deleteProduct(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                ProductService().removeProduct(id)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

}