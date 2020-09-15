package com.example.restservice.DAO

import com.example.restservice.MyConnection
import com.example.restservice.entity.Account
import com.example.restservice.entity.Operator
import com.example.restservice.entity.Roles
import sun.security.util.Debug
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
    remove by id
     */

    fun getOperator(id: Int): Operator? {
        var operator: Operator? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from operator where id = $id;")
            resultSet.next()

            operator = extractOperator(resultSet)
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }

        return operator
    }

    fun getOperators(FIO: String): Array<Operator>? {
        var resultArray = emptyArray<Operator>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from operator where FIO like \"%$FIO%\";")

            resultArray = emptyArray()

            var i = 0
            while (resultSet.next()) {
                resultArray[i] = extractOperator(resultSet)
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

    fun getOperators(): Array<Operator>? {
        var resultArray = emptyArray<Operator>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery("select * from operator;")

            resultArray = emptyArray()

            var i = 0
            while (resultSet.next()) {
                resultArray[i] = extractOperator(resultSet)
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

    private fun extractOperator(resultSet: ResultSet): Operator {
        val id = resultSet.getInt(1)
        val FIO = resultSet.getString(2)
        val phoneNumber = resultSet.getString(3)

        val accountId = resultSet.getInt(4)
        val account = DAOAccount().getAccount(accountId)

        return Operator(id, FIO, phoneNumber, account!!)
    }

    fun insertOperator (operator: Operator) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "insert into Operator(\n" +
                            "ID, FIO, Phone_number, Account_id\n" +
                            ") values (\n" +
                            "${operator.id}, ${operator.FIO}," +
                            "${operator.phoneNumber}, ${operator.account.id}\n" +
                            ");")
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun updateOperator (id: Int, operator: Operator) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "update operator set\n" +
                            "FIO = ${operator.FIO}, Phone_number = ${operator.phoneNumber}\n" +
                            "where id = $id;")

        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

    fun removeOperator (id: Int) {
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            statement = MyConnection.connection.createStatement()
            resultSet = statement.executeQuery(
                    "delete from operator where id = $id;")
            DAOAccount().removeAccount(getOperator(id)!!.account.id) //так же удаляет аккаунт привязаный к юзеру
        } catch (e: SQLException) {
            Debug.println("Error!", "SQLException: ${e.errorCode}")
        } finally {
            resultSet!!.close()
            statement!!.close()
        }
    }

}