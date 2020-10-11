package com.example.restservice.controller

import com.example.restservice.entity.Waybill
import com.example.restservice.service.WaybillService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class WaybillController {

    @PutMapping("/waybill")
    fun insertWaybill(
            @RequestBody waybill: Waybill
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if(
                WaybillService().insertWaybill(waybill)
        ){
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/waybills")
    fun getAllWaybills(): List<Waybill> {
        return WaybillService().getAllWaybills()
    }

    @GetMapping("/waybill")
    fun getWaybill(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): Waybill {
        return WaybillService().getWaybill(id)
    }

    @PatchMapping("/waybill")
    fun updateWaybill(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestBody waybill: Waybill
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                WaybillService().updateWaybill(id,waybill)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/waybill")
    fun deleteWaybill(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                WaybillService().removeWaybill(id)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

}