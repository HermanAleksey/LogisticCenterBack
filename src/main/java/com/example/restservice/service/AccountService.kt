package com.example.restservice.service

import com.example.restservice.DAO.DAOAccount
import com.example.restservice.entity.Account

class AccountService {
    //return account with id = id
    fun getAccount(id: Int): Account {
        return DAOAccount().getAccount(id)
    }

    //return all account in DB
    fun getAllAccounts(): List<Account>{
        return DAOAccount().getAccounts()
    }

    //insert account in DB
    // return true in case of success
    fun insertAccount(account: Account): Boolean{
        return DAOAccount().insertAccount(account)
    }

    //redact account in bd
    //true if success
    fun updateAccount(id: Int, account: Account): Boolean{
        return DAOAccount().updateAccount(id,account)
    }
}