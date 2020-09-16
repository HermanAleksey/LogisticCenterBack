package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.CenterProduct
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

    fun getCenterProductByCenterId(id: Int): CenterProduct? {
        var centerProduct: CenterProduct? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product where center_id = $id;")
        resultSet.next()

        centerProduct = extractCenterProduct(resultSet)

        return centerProduct
    }

    fun getCenterProductByProductId(id: Int): CenterProduct? {
        var centerProduct: CenterProduct? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product where product_id = $id;")
        resultSet.next()

        centerProduct = extractCenterProduct(resultSet)

        return centerProduct
    }

    fun getCenterProductById(id: Int): CenterProduct? {
        var centerProduct: CenterProduct? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from center_product where id = $id;")
        resultSet.next()

        centerProduct = extractCenterProduct(resultSet)

        return centerProduct
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

    fun insertCenterProduct(centerProduct: CenterProduct) {
        var statement: Statement? = null

        statement = MyConnection.connection.createStatement()
        statement.execute(
                "insert into center_product(\n" +
                        "ID, Center_id, Product_id\n" +
                        ") values (\n" +
                        "${centerProduct.id}, ${centerProduct.center.id}," +
                        " ${centerProduct.product.id}" +
                        ");")
    }

    fun updateCenterProduct(id: Int, centerProduct: CenterProduct) {
        var statement: Statement? = null

        statement = MyConnection.connection.createStatement()
        statement.executeQuery(
                "update center_product set\n" +
                        "Center_id = ${centerProduct.center.id}," +
                        "Product_id = ${centerProduct.product.id}" +
                        "where id = $id;")
    }

    fun removeCenterProductById(id: Int) {
        var statement: Statement? = null

        statement = MyConnection.connection.createStatement()
        statement.execute(
                "delete from center_product where id = $id;")
    }

    fun removeCenterProductsByCenterId(id: Int) {
        var statement: Statement? = null

        statement = MyConnection.connection.createStatement()
        statement.executeQuery(
                "delete from center_product where center_id = $id;")
    }

    fun removeCenterProductsByProductId(id: Int) {
        var statement: Statement? = null

        statement = MyConnection.connection.createStatement()
        statement.executeQuery(
                "delete from center_product where product_id = $id;")
    }

}