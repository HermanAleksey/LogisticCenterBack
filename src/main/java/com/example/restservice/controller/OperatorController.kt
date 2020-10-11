//package com.example.restservice.controller
//
//import com.example.restservice.entity.Operator
//import com.example.restservice.service.OperatorService
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//
//@RestController
//class OperatorController {
//
//    @PutMapping("/operator")
//    fun insertOperator(
//            @RequestBody operator: Operator
//    ): ResponseEntity<Any> {
//        val httpHeaders = HttpHeaders()
//
//        return if(
//                OperatorService().insertOperator(operator)
//        ){
//            ResponseEntity(httpHeaders, HttpStatus.OK)
//        } else ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND)
//    }
//
//    @GetMapping("/operators")
//    fun getAllOperators(): List<Operator> {
//        return OperatorService().getAllOperators()
//    }
//
//    @GetMapping("/operator")
//    fun getOperator(
//            @RequestParam(value = "id", defaultValue = "1") id: Int
//    ): Operator {
//        return OperatorService().getOperator(id)
//    }
//
//    @GetMapping("/operator")
//    fun getOperator(
//            @RequestParam(value = "fio", defaultValue = "default") FIO: String
//    ): List<Operator> {
//        return OperatorService().getOperatorsByFIO(FIO)
//    }
//
//    @PatchMapping("/operator")
//    fun updateOperator(
//            @RequestParam(value = "id", defaultValue = "1") id: Int,
//            @RequestBody operator: Operator
//    ): ResponseEntity<Any> {
//        val httpHeaders = HttpHeaders()
//
//        return if (
//                OperatorService().updateOperator(id,operator)
//        ) {
//            ResponseEntity(httpHeaders, HttpStatus.OK)
//        } else ResponseEntity(HttpStatus.NOT_FOUND)
//    }
//}
