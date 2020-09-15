package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.Product
import sun.security.util.Debug
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

    fun getProduct(id: Int): Product? {
        var product: Product? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from Product where id = $id;")
            resultSet.next()

            product = extractProduct(resultSet)
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return product
    }

    fun getProducts(): Array<Product>? {
        var resultArray = emptyArray<Product>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from Product;")

            resultArray = emptyArray()

            var i = 0
            while (resultSet.next()) {
                resultArray[i] = extractProduct(resultSet)
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

    private fun extractProduct(resultSet: ResultSet): Product {
        val id = resultSet.getInt(1)
        val article = resultSet.getString(2)
        val title = resultSet.getString(3)
        val amount = resultSet.getInt(4)
        val waybillId = resultSet.getInt(5)

        val waybill = DAOWaybill().getWaybill(waybillId)!!

        return Product(id, article, title, amount, waybill)
    }

    fun insertProduct(product: Product) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "insert into Product(\n" +
                            "Article, Title, Amount, Waybill_id\n" +
                            ") values (\n" +
                            "${product.article}," +
                            "${product.title}," +
                            "${product.amount}," +
                            "${product.waybill.id}" +
                            ");")
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun updateProduct(id: Int, product: Product) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "update Product set\n" +
                            "Article = ${product.article}," +
                            "Title = ${product.title}," +
                            "Amount = ${product.amount}," +
                            "Waybill_id = ${product.waybill.id}" +
                            "where id = $id;")

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun removeProduct(id: Int) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            //Удаление также из таблицы Center-Product
            DAOCenterProduct().removeCenterProductsByProductId(id)

            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "delete from Product where id = $id;")

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

}