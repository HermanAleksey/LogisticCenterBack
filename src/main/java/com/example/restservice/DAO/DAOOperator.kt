package com.example.restservice.DAO

import com.example.restservice.MyConnection
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

    fun getOperator(id: Int): Operator? {
        var operator: Operator? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from operator where id = $id;")
        resultSet.next()

        operator = extractOperator(resultSet)

        return operator
    }

    fun getOperators(FIO: String): Array<Operator>? {
        var resultArray = emptyArray<Operator>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from operator where FIO like \"%$FIO%\";")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractOperator(resultSet)
            i++
        }

        return resultArray
    }

    fun getOperators(): Array<Operator>? {
        var resultArray = emptyArray<Operator>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = MyConnection.connection.createStatement()
        resultSet = statement.executeQuery("select * from operator;")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractOperator(resultSet)
            i++
        }

        return resultArray
    }

    private fun extractOperator(resultSet: ResultSet): Operator {
        val id = resultSet.getInt(1)
        val FIO = resultSet.getString(2)
        val phoneNumber = resultSet.getString(3)

        val accountId = resultSet.getInt(4)
        val account = DAOAccount().getAccount(accountId)

        return Operator(id, FIO, phoneNumber, account!!)
    }

    fun insertOperator(operator: Operator): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "insert into Operator(\n" +
                            "ID, FIO, Phone_number, Account_id\n" +
                            ") values (\n" +
                            "${operator.id}, \"${operator.FIO}\"," +
                            "\"${operator.phoneNumber}\", ${operator.account.id}\n" +
                            ");")
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateOperator(id: Int, operator: Operator): Boolean {
        var statement: Statement? = null

        return try {
            statement = MyConnection.connection.createStatement()
            statement.execute(
                    "update operator set\n" +
                            "FIO = \"${operator.FIO}\", Phone_number = \"${operator.phoneNumber}\"" +
                            "where id = $id;")
            true
        } catch (e: Exception) {
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