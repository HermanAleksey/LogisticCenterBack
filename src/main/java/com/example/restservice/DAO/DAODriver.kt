package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.Account
import com.example.restservice.entity.Driver
import com.example.restservice.entity.Operator
import com.example.restservice.entity.Roles
import sun.security.util.Debug
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

    fun getDriver(id: Int): Driver?{
        var driver: Driver? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from driver where id = $id;")
            resultSet.next()

            driver = extractDriver(resultSet)
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return driver
    }

    fun getDrivers(): Array<Driver>? {
        var resultArray = emptyArray<Driver>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from driver;")

            resultArray = emptyArray()

            var i = 0
            while (resultSet.next()) {
                resultArray[i] = extractDriver(resultSet)
                i++
            }

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return resultArray
    }

    private fun extractDriver(resultSet: ResultSet): Driver {
        val id = resultSet.getInt(1)
        val FIO = resultSet.getString(2)
        val phoneNumber = resultSet.getString(3)

        return Driver(id, FIO, phoneNumber)
    }

    fun insertDriver (driver: Driver) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "insert into Driver(\n" +
                            "ID, FIO, Phone_number\n" +
                            ") values (\n" +
                            "${driver.id}, ${driver.FIO}, ${driver.phoneNumber}" +
                            ");")
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun updateDriver (id: Int, driver: Driver) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "update driver set\n" +
                            "FIO = ${driver.FIO}, Phone_number = ${driver.phoneNumber}\n" +
                            "where id = $id;")

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun removeDriver (id: Int) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "delete from Driver where id = $id;")
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }
}