package com.example.restservice.service

import com.example.restservice.DAO.DAOCenterProduct
import com.example.restservice.entity.CenterProduct
import com.example.restservice.entity.CenterProductId

class CenterProductService {

    //second param describe by witch id select objects
    fun getCenterProductById(id: Int, centerProductId: CenterProductId): List<CenterProduct>{
        return DAOCenterProduct().getCenterProductById(id,centerProductId)
    }

    fun getAllCenterProduct(): List<CenterProduct>{
        return DAOCenterProduct().getCenterProducts()
    }

    fun insertCenterProduct(centerProduct: CenterProduct): Boolean{
        return DAOCenterProduct().insertCenterProduct(centerProduct)
    }

    fun updateCenterProduct(id: Int,centerProduct: CenterProduct): Boolean{
        return DAOCenterProduct().updateCenterProduct(id, centerProduct)
    }

    //second param describe by witch id select objects
    fun removeCenterProductById(id: Int, centerProductId: CenterProductId): Boolean{
        return DAOCenterProduct().removeCenterProductById(id, centerProductId)
    }

}