package com.example.restservice.controller

import com.example.restservice.entity.Account
import com.example.restservice.entity.Driver
import com.example.restservice.service.AccountService
import com.example.restservice.service.CenterService
import com.example.restservice.service.DriverService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DriverController {

    @PutMapping("/driver")
    fun insertDriver(
            @RequestBody driver: Driver
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if(
                DriverService().insertDriver(driver)
        ){
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/drivers")
    fun getAllDrivers(): List<Driver> {
        return DriverService().getAllDrivers()
    }

    @GetMapping("/driver")
    fun getDriver(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): Driver {
        return DriverService().getDriver(id)
    }

    @PatchMapping("/driver")
    fun updateDriver(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestBody driver: Driver
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                DriverService().updateDriver(id,driver)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/driver")
    fun deleteDriver(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                DriverService().removeDriver(id)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

}