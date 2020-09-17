package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.CenterProductId
import com.example.restservice.entity.LogisticsCenter
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAOCenter {

    /*
   select by id
   select all
   insert
   update by id + new object
   remove by id
    */

    fun getCenter(id: Int): LogisticsCenter? {
        var logisticsCenter: LogisticsCenter? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from logistic_center where id = $id;")
        resultSet.next()

        logisticsCenter = extractCenter(resultSet)

        return logisticsCenter
    }

    fun getCenters(): Array<LogisticsCenter>? {
        var resultArray = emptyArray<LogisticsCenter>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from logistic_center;")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractCenter(resultSet)
            i++
        }

        return resultArray
    }

    private fun extractCenter(resultSet: ResultSet): LogisticsCenter {
        val id = resultSet.getInt(1)
        val name = resultSet.getString(2)
        val location = resultSet.getString(3)

        return LogisticsCenter(id, name, location)
    }

    fun insertCenter(center: LogisticsCenter): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into logistic_center(\n" +
                            "Name, Location" +
                            ") values (\n" +
                            "\"${center.name}\", \"${center.location}\"" +
                            ");")
            true
        } catch (e: Exception){
            false
        }
    }

    fun updateCenter(id: Int, center: LogisticsCenter): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update logistic_center set\n" +
                            "Name = \"${center.name}\"," +
                            "Location = \"${center.location}\"" +
                            "where id = $id;")
            true
        } catch (e: Exception){
            false
        }
    }

    fun removeCenter(id: Int): Boolean {
        var statement: Statement? = null

        return try {
            //Удаление также из таблицы Center-Product
            DAOCenterProduct().removeCenterProductById(id)

            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from logistic_center where id = $id;")
            true
        } catch (e: Exception){
            false
        }

    }
}