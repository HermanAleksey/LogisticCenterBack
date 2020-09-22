package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.Tests
import com.example.restservice.entity.Account
import com.example.restservice.entity.CenterProductId
import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.entity.Operator
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

    fun getCenter(id: Int): LogisticsCenter {
        val logisticsCenter: LogisticsCenter
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from logistic_center where id = $id;")

        if (!resultSet.next()){
            Tests().printlnBlue("Error. Table logistic_center is empty")
            return LogisticsCenter(1,"default", "default")
        }

        logisticsCenter = extractCenter(resultSet)

        Tests().printlnBlue("Accepted object: $logisticsCenter")

        return logisticsCenter
    }

    fun getCenters(): List<LogisticsCenter> {
        val resultArray =  arrayOfNulls<LogisticsCenter>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from logistic_center;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractCenter(resultSet)
            i++
        }

        val noNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${noNullsArray.size} objects of class \"LogisticCenter\"")

        return noNullsArray
    }

    private fun extractCenter(resultSet: ResultSet): LogisticsCenter {
        val id = resultSet.getInt(1)
        val name = resultSet.getString(2)
        val location = resultSet.getString(3)

        return LogisticsCenter(id, name, location)
    }

    fun insertCenter(center: LogisticsCenter): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into logistic_center(\n" +
                            "Name, Location" +
                            ") values (\n" +
                            "\"${center.name}\", \"${center.location}\"" +
                            ");")
            Tests().printlnBlue("Inserted object $center")
            true
        } catch (e: Exception){
            Tests().printlnBlue("Error. Object $center is not inserted")
            false
        }
    }

    fun updateCenter(id: Int, center: LogisticsCenter): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update logistic_center set\n" +
                            "Name = \"${center.name}\"," +
                            "Location = \"${center.location}\" " +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $center")
            true
        } catch (e: Exception){
            Tests().printlnBlue("Error. Object $center is not updated")
            false
        }
    }

    fun removeCenter(id: Int): Boolean {
        val statement: Statement

        return try {
            //Удаление также из таблицы Center-Product
            DAOCenterProduct().removeCenterProductById(id, CenterProductId.CENTER_ID)

            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from logistic_center where id = $id;")
            Tests().printlnBlue("Object Center with id = $id is deleted")
            true
        } catch (e: Exception){
            Tests().printlnBlue("Error. Object Center with id = $id is not deleted")
            false
        }

    }
}