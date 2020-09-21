package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.Tests
import com.example.restservice.entity.Account
import com.example.restservice.entity.Operator
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAOOperator {

    /*
    select by id
    select by FIO
    select all
    insert
    update by id + new object
    -----  remove by id
     */

    fun getOperator(id: Int): Operator {
        val operator: Operator
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from operator where id = $id;")

        if (!resultSet.next()){
            Tests().printlnBlue("Error. Table operator is empty")
            return Operator(1,"default", "default", Account(1))
        }

        operator = extractOperator(resultSet)

        Tests().printlnBlue("Accepted object: $operator")

        return operator
    }

    fun getOperators(FIO: String): List<Operator> {
        val resultArray = arrayOfNulls<Operator>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from operator where FIO like \"%$FIO%\";")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractOperator(resultSet)
            i++
        }

        val notNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${notNullsArray.size} objects of class \"Operator\"")

        return notNullsArray
    }

    fun getOperators(): List<Operator> {
        val resultArray = arrayOfNulls<Operator>(1000)
        val resultSet: ResultSet

        val statement: Statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from operator;")

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractOperator(resultSet)
            i++
        }

        val notNullsArray = resultArray.filterNotNull()

        Tests().printlnBlue("Accepted ${notNullsArray.size} objects of class \"Operator\"")

        return notNullsArray
    }

    private fun extractOperator(resultSet: ResultSet): Operator {
        val id = resultSet.getInt(1)
        val FIO = resultSet.getString(2)
        val phoneNumber = resultSet.getString(3)

        val accountId = resultSet.getInt(4)
        val account = DAOAccount().getAccount(accountId)

        return Operator(id, FIO, phoneNumber, account)
    }

    fun insertOperator(operator: Operator): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into Operator(\n" +
                            "ID, FIO, Phone_number, Account_id\n" +
                            ") values (\n" +
                            "${operator.id}, \"${operator.FIO}\"," +
                            "\"${operator.phoneNumber}\", ${operator.account.id}\n" +
                            ");")
            Tests().printlnBlue("Inserted object $operator")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $operator is not inserted")
            false
        }
    }

    fun updateOperator(id: Int, operator: Operator): Boolean {
        val statement: Statement

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update operator set\n" +
                            "FIO = \"${operator.FIO}\", Phone_number = \"${operator.phoneNumber}\"" +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $operator")
            true
        } catch (e: Exception) {
            Tests().printlnBlue("Error. Object $operator is not updated")
            false
        }
    }

//    fun removeOperator (id: Int) {
//        var statement: Statement? = null
//        var resultSet: ResultSet? = null
//
//        try {
//            statement = MyConnection.connection.createStatement()
//            resultSet = statement.executeQuery(
//                    "delete from operator where id = $id;")
//            DAOAccount().removeAccount(getOperator(id)!!.account.id) //так же удаляет аккаунт привязаный к юзеру
//        } catch (e: SQLException) {
//            println("Error!, SQLException: ${e.errorCode}")
//        } finally {
//            resultSet!!.close()
//            statement!!.close()
//        }
//    }

}