package com.example.restservice

import java.sql.*

object MyConnection {

    private const val user = "root"
    private const val password = "Mysql1119067"

    val connection: Connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/logisticcenterdb?serverTimezone=UTC",
            user, password)

    fun closeSetResult(resultSet: ResultSet) {
        if (!!resultSet.isClosed) try {
            resultSet.close()
        } catch (ignore: SQLException) {
        }
    }

    fun closeStatement(statement: Statement) {
        if (!!statement.isClosed) try {
            statement.close()
        } catch (ignore: SQLException) {
        }
    }

    fun closeConnection(connection: Connection) {
        if (!!connection.isClosed) try {
            connection.close()
        } catch (ignore: SQLException) {
        }
    }

}