package com.example.restservice.service

import com.example.restservice.DAO.DAODriver
import com.example.restservice.DAO.DAOProduct
import com.example.restservice.DAO.DAOWaybill
import com.example.restservice.entity.Waybill

class WaybillService {

    fun getWaybill(id: Int): Waybill {
        return DAOWaybill().getWaybill(id)
    }

    fun getAllWaybills(): List<Waybill> {
        return DAOWaybill().getWaybills()
    }

    fun insertWaybill(waybill: Waybill): Boolean {
        return DAOWaybill().insertWaybill(waybill)
    }

    fun updateWaybill(id: Int, waybill: Waybill): Boolean {
        return DAOWaybill().updateWaybill(id, waybill)
    }

    fun removeWaybill(id: Int): Boolean {
        val productsArray = DAOProduct().getProducts()
        productsArray!!.forEach {
            if (it.waybill.id == id) {
                return false
            }
        }

        return DAOWaybill().removeWaybill(id)
    }
}