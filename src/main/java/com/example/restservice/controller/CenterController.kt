package com.example.restservice.controller

import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.service.CenterService
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
class CenterController {

    @PutMapping("/center")
    fun insertCenter(
            @RequestBody logisticsCenter: LogisticsCenter
    ): ResponseEntity<Any>{
        val httpHeaders = HttpHeaders()

        return if(
                CenterService().insertCenter(logisticsCenter)
        ){
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
    }

    @GetMapping("/centers")
    fun getAllCenters(): List<LogisticsCenter> {
        return CenterService().getAllCenters()
    }

    @GetMapping("/center")
    fun getCenter(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): LogisticsCenter {
        return CenterService().getCenter(id)
    }

    @PatchMapping("/center")
    fun updateCenter(
            @RequestParam(value = "id", defaultValue = "1") id: Int,
            @RequestBody logisticsCenter: LogisticsCenter
    ): ResponseEntity<Any>{
        val httpHeaders = HttpHeaders()

        return if (
                CenterService().updateCenter(id,logisticsCenter)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/center")
    fun deleteCenter(
            @RequestParam(value = "id", defaultValue = "1") id: Int
    ): ResponseEntity<Any> {
        val httpHeaders = HttpHeaders()

        return if (
                CenterService().removeCenter(id)
        ) {
            ResponseEntity(httpHeaders, HttpStatus.OK)
        } else ResponseEntity(HttpStatus.NOT_FOUND)
    }
}