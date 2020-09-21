package com.example.restservice.DAO

import com.example.restservice.MyConnection.connection
import com.example.restservice.Tests
import com.example.restservice.entity.Account
import com.example.restservice.entity.Operator
import com.example.restservice.entity.Roles
import java.lang.Exception
import java.sql.ResultSet
import java.sql.Statement

class DAOAccount {

    /*
    select by id
    select all
    insert
    update by id + new object
    ----- remove by id
     */

    fun getAccount(id: Int): Account {
        val account: Account
        val resultSet: ResultSet

        val statement: Statement = connection.createStatement()
        resultSet = statement.executeQuery("select * from account where id = $id;")

        if (!resultSet.next()){
            Tests().printlnBlue("Error. Table account is empty")
            return Account(1,"default", "default")
        }

        account = extractAccount(resultSet)

        Tests().printlnBlue("Accepted object: $account")

        return account
    }

    fun getAccounts(): List<Account> {
        val array = arrayOfNulls<Account>(1000)
        val resultSet: ResultSet

        val statement: Statement = connection.createStatement()
        resultSet = statement.executeQuery("select * from account;")

        var i = 0
        while (resultSet.next()) {
            array[i] = extractAccount(resultSet)
            i++
        }

        val noNullsArray = array.filterNotNull()

        Tests().printlnBlue("Accepted ${noNullsArray.size} objects of class \"Account\"")

        return noNullsArray
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
        val statement: Statement

        return try {
            statement = connection.createStatement()
            statement.execute(
                    "insert into Account(\n" +
                            "login, pass, role_id\n" +
                            ") values (\n" +
                            "\"${account.login}\", \"${account.password}\", ${Roles.Driver.getPosition(account.role)}\n" +
                            ");")

            Tests().printlnBlue("Inserted object $account")
            true
        } catch (e: Exception){
            Tests().printlnBlue("Error. Object $account is not inserted")
            false
        }

    }

    fun updateAccount(id: Int, account: Account) : Boolean {
        val statement: Statement

        return try {
            statement = connection.createStatement()
            statement.execute(
                    "update account set\n" +
                            "login = \"${account.login}\", pass = \"${account.password}\"" +
                            "where id = $id;")
            Tests().printlnBlue("Updated object $account")
            true
        } catch (e: Exception){
            Tests().printlnBlue("Error. Object $account is not updated")
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