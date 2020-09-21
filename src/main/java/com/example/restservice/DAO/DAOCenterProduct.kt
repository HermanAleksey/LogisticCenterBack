package com.example.restservice.DAO

import com.example.restservice.MyConnection
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

    fun getCenterProductById(id: Int, centerProductId: CenterProductId): Array<CenterProduct>? {
        var resultArray = emptyArray<CenterProduct>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product where $centerProductId = $id;")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractCenterProduct(resultSet)
            i++
        }

        return resultArray
    }

    fun getCenterProducts(): Array<CenterProduct>? {
        var resultArray = emptyArray<CenterProduct>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product;")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractCenterProduct(resultSet)
            i++
        }

        return resultArray
    }

    private fun extractCenterProduct(resultSet: ResultSet): CenterProduct {
        val id = resultSet.getInt(1)
        val centerId = resultSet.getInt(2)
        val productId = resultSet.getInt(3)

        val center = DAOCenter().getCenter(centerId)!!
        val product = DAOProduct().getProduct(productId)!!

        return CenterProduct(id, center, product)
    }

    fun insertCenterProduct(centerProduct: CenterProduct): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into center_product(\n" +
                            "ID, Center_id, Product_id\n" +
                            ") values (\n" +
                            "${centerProduct.id}, ${centerProduct.center.id}," +
                            " ${centerProduct.product.id}" +
                            ");")
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateCenterProduct(id: Int, centerProduct: CenterProduct): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.executeQuery(
                    "update center_product set\n" +
                            "Center_id = ${centerProduct.center.id}," +
                            "Product_id = ${centerProduct.product.id}" +
                            "where id = $id;")
            true
        } catch (e: Exception) {
            false
        }
    }

    fun removeCenterProductById(id: Int, centerProductId: CenterProductId): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from center_product where $centerProductId = $id;")
            true
        } catch (e: Exception) {
            false
        }
    }
}