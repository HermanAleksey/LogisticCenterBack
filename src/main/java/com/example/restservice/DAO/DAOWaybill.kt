package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.Waybill
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAOWaybill {
    /*
    select by id
    select all
    insert
    update by id + new object
    remove by id
     */

    fun getWaybill(id: Int): Waybill? {
        var waybill: Waybill? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from Waybill where id = $id;")
            resultSet.next()

            waybill = extractDriver(resultSet)
        } catch (e: SQLException) {
            println("Error!, SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return waybill
    }

    fun getWaybills(): Array<Waybill>? {
        var resultArray = emptyArray<Waybill>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from Waybill;")

            resultArray = emptyArray()

            var i = 0
            while (resultSet.next()) {
                resultArray[i] = extractDriver(resultSet)
                i++
            }

        } catch (e: SQLException) {
            println("Error!, SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return resultArray
    }

    private fun extractDriver(resultSet: ResultSet): Waybill {
        val id = resultSet.getInt(1)
        val dateOfDelivery = resultSet.getString(2)
        val dateOfShipment = resultSet.getString(3)
        val driverId = resultSet.getInt(4)
        val operatorId = resultSet.getInt(5)

        val driver = DAODriver().getDriver(driverId)!!
        val operator = DAOOperator().getOperator(operatorId)!!

        return Waybill(id, dateOfDelivery, dateOfShipment, driver, operator)
    }

    fun insertWaybill(waybill: Waybill): Boolean {
        var statement: Statement? = null

        return try {
        statement = MyConnection.connection.createStatement()
        statement.execute(
                "insert into Waybill(\n" +
                        "ID, date_of_delivery,date_of_shipment, driver_id,operator_id\n" +
                        ") values (\n" +
                        "${waybill.id}, \"${waybill.dateOfDelivery}\", \"${waybill.dateOfShipment}\"," +
                        "${waybill.driver.id}, ${waybill.operator.id}" +
                        ");")
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateWaybill(id: Int, waybill: Waybill): Boolean {
        var statement: Statement? = null

        return try {
        statement = MyConnection.connection.createStatement()
        statement.execute(
                "update waybill set\n" +
                        "FIO = \"${waybill.dateOfDelivery}\"," +
                        "Phone_number = \"${waybill.dateOfShipment}\"," +
                        "Driver_id = ${waybill.driver.id}," +
                        "Operator_id = ${waybill.operator.id}" +
                        "where id = $id;")
            true
        } catch (e: Exception) {
            false
        }
    }

    fun removeWaybill(id: Int): Boolean {
        var statement: Statement? = null

        return try {
        statement = MyConnection.connection.createStatement()
        statement.executeQuery(
                "delete from waybill where id = $id;")
            true
        } catch (e: Exception) {
            false
        }
    }
}