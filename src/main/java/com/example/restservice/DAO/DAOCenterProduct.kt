package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.Tests
import com.example.restservice.entity.CenterProduct
import com.example.restservice.entity.CenterProductId
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAOCenterProduct {

    /*
    select by center id
    select by product id
    select by id
    select all
    insert
    update by id + new object
    remove by id
    remove by center id
    remove by product id
     */

    fun getCenterProductById(id: Int, centerProductId: CenterProductId): List<CenterProduct> {
        val resultArray = arrayOfNulls<CenterProduct>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product where $centerProductId = $id;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractCenterProduct(resultSet)
            i++
        }

        val noNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${noNullsArray.size} objects of class \"CenterProduct\"")

        return noNullsArray
    }

    fun getCenterProducts(): List<CenterProduct> {
        val resultArray = arrayOfNulls<CenterProduct>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractCenterProduct(resultSet)
            i++
        }

        val noNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${noNullsArray.size} objects of class \"CenterProduct\"")

        return noNullsArray
    }

    private fun extractCenterProduct(resultSet: ResultSet): CenterProduct {
        val id = resultSet.getInt(1)
        val centerId = resultSet.getInt(2)
        val productId = resultSet.getInt(3)

        val center = DAOCenter().getCenter(centerId)
        val product = DAOProduct().getProduct(productId)

        return CenterProduct(id, center, product)
    }

    fun insertCenterProduct(centerProduct: CenterProduct): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into center_product(\n" +
                            "ID, Center_id, Product_id\n" +
                            ") values (\n" +
                            "${centerProduct.id}, ${centerProduct.center.id}," +
                            " ${centerProduct.product.id}" +
                            ");")
            Tests().printlnBlue("Inserted object $centerProduct")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $centerProduct is not inserted")
            false
        }
    }

    fun updateCenterProduct(id: Int, centerProduct: CenterProduct): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.executeQuery(
                    "update center_product set\n" +
                            "Center_id = ${centerProduct.center.id}," +
                            "Product_id = ${centerProduct.product.id}" +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $centerProduct")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $centerProduct is not updated")
            false
        }
    }

    fun removeCenterProductById(id: Int, centerProductId: CenterProductId): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from center_product where $centerProductId = $id;")
            Tests().printlnBlue("Error. Object with $id is deleted")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object with id = $id is not deleted")
            false
        }
    }
}