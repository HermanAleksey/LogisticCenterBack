package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.entity.Product
import sun.security.util.Debug
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

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from logistic_center where id = $id;")
            resultSet.next()

            logisticsCenter = extractCenter(resultSet)
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return logisticsCenter
    }

    fun getCenters(): Array<LogisticsCenter>? {
        var resultArray = emptyArray<LogisticsCenter>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from logistic_center;")

            resultArray = emptyArray()

            var i = 0
            while (resultSet.next()) {
                resultArray[i] = extractCenter(resultSet)
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

    private fun extractCenter(resultSet: ResultSet): LogisticsCenter {
        val id = resultSet.getInt(1)
        val name = resultSet.getString(2)
        val location = resultSet.getString(3)

        return LogisticsCenter(id, name, location)
    }

    fun insertProduct(center: LogisticsCenter) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "insert into logistic_center(\n" +
                            "Name, Location" +
                            ") values (\n" +
                            "${center.name}, ${center.location}" +
                            ");")
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun updateCenter(id: Int, center: LogisticsCenter) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "update logistic_center set\n" +
                            "Name = ${center.name}," +
                            "Location = ${center.location}" +
                            "where id = $id;")

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun removeCenter(id: Int) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            //Удаление также из таблицы Center-Product
            DAOCenterProduct().removeCenterProductsByCenterId(id)

            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "delete from logistic_center where id = $id;")

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }
}