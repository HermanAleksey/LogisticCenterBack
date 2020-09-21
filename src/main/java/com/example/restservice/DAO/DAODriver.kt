package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.Tests
import com.example.restservice.entity.Account
import com.example.restservice.entity.Driver
import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.entity.Operator
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAODriver {
    /*
    select by id
    select all
    insert
    update by id + new object
    remove by id
     */

    fun getDriver(id: Int): Driver {
        val driver: Driver
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from driver where id = $id;")

        if (!resultSet.next()){
            Tests().printlnBlue("Error. Table driver is empty")
            return Driver(1,"default", "default")
        }


        driver = extractDriver(resultSet)

        Tests().printlnBlue("Accepted object: $driver")

        return driver
    }

    fun getDrivers(): List<Driver> {
        val resultArray = arrayOfNulls<Driver>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from driver;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractDriver(resultSet)
            i++
        }

        val noNulls = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${noNulls.size} objects of class \"Driver\"")

        return noNulls
    }

    private fun extractDriver(resultSet: ResultSet): Driver {
        val id = resultSet.getInt(1)
        val FIO = resultSet.getString(2)
        val phoneNumber = resultSet.getString(3)

        return Driver(id, FIO, phoneNumber)
    }

    fun insertDriver(driver: Driver): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into Driver(\n" +
                            "ID, FIO, Phone_number\n" +
                            ") values (\n" +
                            "${driver.id}, \"${driver.FIO}\", \"${driver.phoneNumber}\"" +
                            ");")
            Tests().printlnBlue("Inserted object $driver")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $driver is not inserted")
            false
        }
    }

    fun updateDriver(id: Int, driver: Driver): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update driver set\n" +
                            "FIO = \"${driver.FIO}\", Phone_number = \"${driver.phoneNumber}\"" +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $driver")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $driver is not updated")
            false
        }

    }

    fun removeDriver(id: Int): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from Driver where id = $id;")
            Tests().printlnBlue("Object with $id is deleted")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object with id = $id is not deleted")
            false
        }
    }
}