package com.example.restservice.service

import com.example.restservice.DAO.DAOCenter
import com.example.restservice.DAO.DAODriver
import com.example.restservice.DAO.DAOWaybill
import com.example.restservice.entity.Driver
import com.example.restservice.entity.LogisticsCenter

class DriverService {


    fun getDriver(id: Int): Driver {
        return DAODriver().getDriver(id)
    }

    fun getAllDrivers(): List<Driver>{
        return DAODriver().getDrivers()
    }

    fun insertDriver(driver: Driver): Boolean{
        return DAODriver().insertDriver(driver)
    }

    fun updateDriver (id: Int, driver: Driver): Boolean{
        return DAODriver().updateDriver(id,driver)
    }

    // can delete Driver only if he not used in waybills
    fun removeDriver(id: Int): Boolean{
        val waybillsArray = DAOWaybill().getWaybills()
        waybillsArray!!.forEach {
            if (it.driver.id == id){
                return false
            }
        }

        return DAODriver().removeDriver(id)
    }

}