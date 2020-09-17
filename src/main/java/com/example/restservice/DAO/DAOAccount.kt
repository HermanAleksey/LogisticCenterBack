package com.example.restservice.DAO

import com.example.restservice.MyConnection.connection
import com.example.restservice.entity.Account
import com.example.restservice.entity.Roles
import com.sun.org.apache.xpath.internal.operations.Bool
import java.lang.Exception
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DAOAccount {

    /*
    select by id
    select all
    insert
    update by id + new object
    ----- remove by id
     */

    fun getAccount(id: Int): Account? {
        var account: Account? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = connection.createStatement()
        resultSet = statement.executeQuery("select * from account where id = $id;")
        resultSet.next()

        account = extractAccount(resultSet)

        return account
    }

    fun getAccounts(): Array<Account>? {
        var resultArray = emptyArray<Account>()
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        statement = connection.createStatement()
        resultSet = statement.executeQuery("select * from account;")

        resultArray = emptyArray()

        var i = 0
        while (resultSet.next()) {
            resultArray[i] = extractAccount(resultSet)
            i++
        }

        return resultArray
    }

    private fun extractAccount(resultSet: ResultSet): Account {
        val id = resultSet.getInt(1)
        val login = resultSet.getString(2)
        val password = resultSet.getString(3)

        val role =
                when (resultSet.getInt(4)) {
                    3 -> Roles.Administrator
                    2 -> Roles.Operator
                    else -> Roles.Driver
                }

        return Account(id, login, password, role)
    }

    fun insertAccount(account: Account): Boolean {
        var statement: Statement? = null

        return try {
            statement = connection.createStatement()
            statement.execute(
                    "insert into Account(\n" +
                            "login, pass, role_id\n" +
                            ") values (\n" +
                            "${account.login}, ${account.password}, ${Roles.Driver.getPosition(account.role)}\n" +
                            ");")
            true
        } catch (e: Exception){
            false
        }

    }

    fun updateAccount(id: Int, account: Account) : Boolean {
        var statement: Statement? = null

        return try {
            statement = connection.createStatement()
            statement.execute(
                    "update account set\n" +
                            "login = ${account.login}, pass = ${account.password}\n" +
                            "where id = $id;")
            true
        } catch (e: Exception){
            false
        }
    }

//    //только если не привязано к пользователю
//    fun removeAccount(id: Int) {
//        var statement: Statement? = null
//        var resultSet: ResultSet? = null
//
//        statement = connection.createStatement()
//        statement.execute(
//                "delete from account where id = $id;")
//
//    }
}