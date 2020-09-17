package com.example.restservice.service

import com.example.restservice.DAO.DAODriver
import com.example.restservice.DAO.DAOWaybill
import com.example.restservice.entity.Driver

class DriverService {


    fun getDriver(id: Int): Driver {
        return DAODriver().getDriver(id)!!
    }

    fun getAllDrivers(): Array<Driver>{
        return DAODriver().getDrivers()!!
    }

    fun redactDriver (id: Int, driver: Driver): Boolean{
        return DAODriver().updateDriver(id,driver)
    }

    // can delete Driver only if he not used in waybills
    fun deleteDriver(id: Int): Boolean{
        val waybillsArray = DAOWaybill().getWaybills()
        waybillsArray!!.forEach {
            if (it.driver.id == id){
                return false
            }
        }

        return DAODriver().removeDriver(id)
    }

}