package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.Tests
import com.example.restservice.entity.CenterProductId
import com.example.restservice.entity.LogisticsCenter
import com.example.restservice.entity.Product
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAOProduct {

    /*
    select by id
    select all
    insert
    update by id + new object
    remove by id
     */

    fun getProduct(id: Int): Product {
        val product: Product
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from Product where id = $id;")
        if (!resultSet.next()){
            Tests().printlnBlue("Error. Table logistic_center is empty")
            return Product(1)
        }

        product = extractProduct(resultSet)

        Tests().printlnBlue("Accepted object: $product")

        return product
    }

    fun getProducts(): List<Product> {
        val resultArray = arrayOfNulls<Product>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from Product;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractProduct(resultSet)
            i++
        }

        val noNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${noNullsArray.size} objects of class \"Product\"")

        return noNullsArray
    }

    private fun extractProduct(resultSet: ResultSet): Product {
        val id = resultSet.getInt(1)
        val article = resultSet.getString(2)
        val title = resultSet.getString(3)
        val amount = resultSet.getInt(4)
        val waybillId = resultSet.getInt(5)

        val waybill = DAOWaybill().getWaybill(waybillId)

        return Product(id, article, title, amount, waybill)
    }

    fun insertProduct(product: Product): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into Product(\n" +
                            "Article, Title, Amount, Waybill_id\n" +
                            ") values (\n" +
                            "\"${product.article}\"," +
                            "\"${product.title}\"," +
                            "${product.amount}," +
                            "${product.waybill.id}" +
                            ");")
            Tests().printlnBlue("Inserted object $product")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $product is not inserted")
            false
        }
    }

    fun updateProduct(id: Int, product: Product): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update Product set\n" +
                            "Article = \"${product.article}\"," +
                            "Title = \"${product.title}\"," +
                            "Amount = ${product.amount}," +
                            "Waybill_id = ${product.waybill.id} " +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $product")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $product is not updated")
            false
        }
    }

    fun removeProduct(id: Int): Boolean {
        val statement: Statement

        return try {
            DAOCenterProduct().removeCenterProductById(id, CenterProductId.PRODUCT_ID)

            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "delete from Product where id = $id;")
            Tests().printlnBlue("Object with $id is deleted")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object with id = $id is not deleted")
            false
        }

    }

}