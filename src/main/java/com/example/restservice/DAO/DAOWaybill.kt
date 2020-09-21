package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.Tests
import com.example.restservice.entity.LogisticsCenter
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

    fun getWaybill(id: Int): Waybill {
        val waybill: Waybill
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from waybill where id = $id;")

        if (!resultSet.next()){
            Tests().printlnBlue("Error. Table waybill is empty")
            return Waybill(1)
        }

        waybill = extractDriver(resultSet)

        Tests().printlnBlue("Accepted object: $waybill")

        return waybill
    }

    fun getWaybills(): List<Waybill> {
        val resultArray = arrayOfNulls<Waybill>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from Waybill;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractDriver(resultSet)
            i++
        }

        val noNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${noNullsArray.size} objects of class \"Waybill\"")

        return noNullsArray
    }

    private fun extractDriver(resultSet: ResultSet): Waybill {
        val id = resultSet.getInt(1)
        val dateOfDelivery = resultSet.getString(2)
        val dateOfShipment = resultSet.getString(3)
        val driverId = resultSet.getInt(4)
        val operatorId = resultSet.getInt(5)

        val driver = DAODriver().getDriver(driverId)
        val operator = DAOOperator().getOperator(operatorId)

        return Waybill(id, dateOfDelivery, dateOfShipment, driver, operator)
    }

    fun insertWaybill(waybill: Waybill): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into Waybill(\n" +
                            "date_of_delivery,date_of_shipment, driver_id,operator_id\n" +
                            ") values (\n" +
                            "\"${waybill.dateOfDelivery}\", \"${waybill.dateOfShipment}\"," +
                            "${waybill.driver.id}, ${waybill.operator.id}" +
                            ");")
            Tests().printlnBlue("Inserted object $waybill")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $waybill is not inserted")
            false
        }
    }

    fun updateWaybill(id: Int, waybill: Waybill): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update waybill set\n" +
                            "FIO = \"${waybill.dateOfDelivery}\"," +
                            "Phone_number = \"${waybill.dateOfShipment}\"," +
                            "Driver_id = ${waybill.driver.id}," +
                            "Operator_id = ${waybill.operator.id}" +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $waybill")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $waybill is not updated")
            false
        }
    }

    fun removeWaybill(id: Int): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.executeQuery(
                    "delete from waybill where id = $id;")
            Tests().printlnBlue("Object with $id is deleted")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object with id = $id is not deleted")
            false
        }
    }
}