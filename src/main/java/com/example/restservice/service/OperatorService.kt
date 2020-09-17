package com.example.restservice.service

import com.example.restservice.DAO.DAOOperator
import com.example.restservice.entity.Operator
import java.sql.Driver

class OperatorService {

    fun getOperator(id: Int): Operator{
        return DAOOperator().getOperator(id)!!
    }

    fun getOperatorsByFIO(FIO: String): Array<Operator>{
        return DAOOperator().getOperators(FIO)!!
    }

    fun getAllOperators(): Array<Operator>{
        return DAOOperator().getOperators()!!
    }

    fun createOperator(operator: Operator): Boolean{
        return DAOOperator().insertOperator(operator)
    }

    fun redactOperator (id: Int,operator: Operator): Boolean{
        return DAOOperator().updateOperator(id,operator)
    }

}