package com.example.restservice.controller

import com.example.restservice.entity.Account
import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.service.AccountService
import com.example.restservice.service.CenterService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@RestController
class AccountController {

    @GetMapping("/account")
    fun getAllAccounts(
            @RequestParam(value = "login", defaultValue = "default") login: String,
            model: MutableMap<String, Any>): String {
        model.put("login", login)

        return "account"
//        return AccountService().getAllAccounts()
    }

    @GetMapping
    fun main(model: MutableMap<String, Any>): String{
        model.put("some", "hello, wrld")
        return "main"
    }

//    @PutMapping("/account")
//    fun insertAccount(
//            @RequestBody account: Account
//    ): ResponseEntity<Any> {
//        val httpHeaders = HttpHeaders()
//
//        return if(
//                AccountService().insertAccount(account)
//        ){
//            ResponseEntity(httpHeaders, HttpStatus.OK)
//        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
//    }
//
//    @GetMapping("/accounts")
//    fun getAllAccounts(): List<Account> {
//        return AccountService().getAllAccounts()
//    }
//
//    @GetMapping("/account")
//    fun getAccount(
//            @RequestParam(value = "id", defaultValue = "1") id: Int
//    ): Account {
//        return AccountService().getAccount(id)
//    }
//
//    @PatchMapping("/account")
//    fun updateAccount(
//            @RequestParam(value = "id", defaultValue = "1") id: Int,
//            @RequestBody account: Account
//    ): ResponseEntity<Any> {
//        val httpHeaders = HttpHeaders()
//
//        return if (
//                AccountService().updateAccount(id,account)
//        ) {
//            ResponseEntity(httpHeaders, HttpStatus.OK)
//        } else ResponseEntity(HttpStatus.NOT_FOUND)
//    }

}