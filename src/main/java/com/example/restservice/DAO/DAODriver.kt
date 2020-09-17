package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.Driver
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

    fun getDriver(id: Int): Driver? {
        var driver: Driver? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from driver where id = $id;")
        resultSet.next()

        driver = extractDriver(resultSet)

        return driver
    }

    fun getDrivers(): Array<Driver>? {
        var resultArray = emptyArray<Driver>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from driver;")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractDriver(resultSet)
            i++
        }

        return resultArray
    }

    private fun extractDriver(resultSet: ResultSet): Driver {
        val id = resultSet.getInt(1)
        val FIO = resultSet.getString(2)
        val phoneNumber = resultSet.getString(3)

        return Driver(id, FIO, phoneNumber)
    }

    fun insertDriver(driver: Driver) {
        var statement: Statement? = null

        statement = MyConnection.connection.createStatement()
        statement.execute(
                "insert into Driver(\n" +
                        "ID, FIO, Phone_number\n" +
                        ") values (\n" +
                        "${driver.id}, \"${driver.FIO}\", \"${driver.phoneNumber}\"" +
                        ");")
    }

    fun updateDriver(id: Int, driver: Driver): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update driver set\n" +
                            "FIO = \"${driver.FIO}\", Phone_number = \"${driver.phoneNumber}\"" +
                            "where id = $id;")
            true
        } catch (e: Exception) {
            false
        }

    }

    fun removeDriver(id: Int): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from Driver where id = $id;")
            true
        } catch (e: Exception) {
            false
        }
    }
}