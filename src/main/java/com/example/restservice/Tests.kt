package com.example.restservice

import com.example.restservice.entity.*
import com.example.restservice.service.*

class Tests {

    val ANSI_BLUE = "\u001B[34m"
    val ANSI_RESET = "\u001B[0m"

    fun printlnBlue(str: String) {
        println("$ANSI_BLUE$str$ANSI_RESET")
    }

    fun doTest() {
//        testAccountFunctions()
//        println("\nNEXT\n")
//        testCenterFunctions()
//        println("\nNEXT\n")
//        testDriverFunctions()
//        println("\nNEXT\n")
//        testOperatorFunctions()
//        println("\nNEXT\n")
//        testWaybillFunctions()
//        println("\nNEXT\n")
//        testProductsFunctions()

        //не проверил.
//        println("\nNEXT\n")
//        testCenterProductFunctions()
    }

    private fun testProductsFunctions() {
        ProductService().let {
            it.getProduct(1)
            //correct

            it.getProducts()
            //correct

            it.insertProduct(Product(1, "Inserted", "line", 3, Waybill(3)))
            //correct

            it.updateProduct(1, Product(1, "Updated", "Field", 3, Waybill(3)))
            //correct

            it.removeProduct(10)
            //correct
        }
    }

    private fun testWaybillFunctions() {
        WaybillService().getWaybill(1)
        //correct

        WaybillService().getAllWaybills()
        //correct

        WaybillService().insertWaybill(Waybill(1, "New DATE Deliv", "Date shipment", Driver(1), Operator(1)))
        //correct

        WaybillService().updateWaybill(1, Waybill(1, "update", "line", Driver(1), Operator(1)))
        //correct

        WaybillService().removeWaybill(1)
        //correct
    }

    private fun testOperatorFunctions() {
        OperatorService().getOperator(1)
        //correct

        OperatorService().getOperatorsByFIO("lesha")
        //correct

        OperatorService().getAllOperators()
        //correct

        OperatorService().insertOperator(Operator(1, "New FIO", "2812482", Account(1)))
        //correct

        OperatorService().updateOperator(1, Operator(1, "Updated FIO", "new number", Account(1)))
        //correct
    }

    private fun testDriverFunctions() {
        DriverService().getDriver(1)
        //correct

        DriverService().getAllDrivers()
        //correct

        DriverService().insertDriver(Driver(1, "New FIO", "2812482"))
        //correct

        DriverService().updateDriver(1, Driver(1, "updated", "driver"))
        //correct

        DriverService().removeDriver(1)
        //correct
    }

    fun testAccountFunctions() {
        val account1: Account = AccountService().getAccount(1)
        println(account1.toString())
        //correct

        val accounts = AccountService().getAllAccounts()
        accounts.forEach {
            println(it)
        }
        //correct

        val account2 = Account(1, "CreatedAccount", "CreatedTest", Roles.Operator)
        AccountService().insertAccount(account2)
        //correct

        val account3 = Account(1, "redactedAccount", "hello", Roles.Operator)
        AccountService().updateAccount(2, account3)
        //correct
    }

    fun testCenterFunctions() {
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