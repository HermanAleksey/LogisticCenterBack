package com.example.restservice

import com.example.restservice.entity.*
import com.example.restservice.service.AccountService
import com.example.restservice.service.CenterService
import com.example.restservice.service.DriverService
import com.example.restservice.service.OperatorService

class Tests {

    val ANSI_BLUE = "\u001B[34m"
    val ANSI_RESET = "\u001B[0m"

    fun printlnBlue(str: String){
        println("$ANSI_BLUE$str$ANSI_RESET")
    }

    fun doTest() {
//        testAccountFunctions()
//        println("\nNEXT\n")
//        testCenterFunctions()
//        println("\nNEXT\n")
//        testDriverFunctions()
        println("\nNEXT\n")
        testOperatorFunctions()

    }

    private fun testOperatorFunctions() {
        OperatorService().getOperator(1)
        //correct

        OperatorService().getOperatorsByFIO("lesha")
        //correct

        OperatorService().getAllOperators()
        //correct

        OperatorService().insertOperator(Operator(1,"New FIO","2812482", Account(1)))
        //correct

        OperatorService().updateOperator(1, Operator(1, "Updated FIO","new number", Account(1)))
        //correct
    }

    private fun testDriverFunctions() {
        DriverService().getDriver(1)
        //correct

        DriverService().getAllDrivers()
        //correct

        DriverService().insertDriver(Driver(1,"New FIO","2812482"))
        //correct

        DriverService().updateDriver(1,Driver(1, "updated","driver"))
        //correct

        DriverService().removeDriver(1)
        //correct
    }

    fun testAccountFunctions(){
        val account1: Account = AccountService().getAccount(1)
        println(account1.toString())
        //correct

        val accounts = AccountService().getAllAccounts()
//        accounts.forEach {
//            println(it)
//        }
        //correct

        val account2 = Account(1, "CreatedAccount", "CreatedTest", Roles.Operator)
        AccountService().insertAccount(account2)
        //correct

        val account3 = Account(1, "redactedAccount", "hello", Roles.Operator)
        AccountService().updateAccount(2, account3)
        //correct
    }

    fun testCenterFunctions(){
        val center1 = CenterService().getCenter(1)
        println(center1)
        //correct

        val centers = CenterService().getAllCenters()
        centers.forEach {
            println(it)
        }
        //correct

        val center2 = LogisticsCenter(1, "Added from", "code #2")
        CenterService().insertCenter(center2)
        //correct

        val center3 = LogisticsCenter(1, "updated center", "from code :)")
        CenterService().updateCenter(1, center3)
        //correct

        CenterService().removeCenter(1)
        //correct
    }
}