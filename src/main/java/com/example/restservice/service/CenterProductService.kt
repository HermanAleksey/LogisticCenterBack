package com.example.restservice.service

import com.example.restservice.DAO.DAOCenterProduct
import com.example.restservice.entity.CenterProduct
import com.example.restservice.entity.CenterProductId

class CenterProductService {

    //second param describe by witch id select objects
    fun getCenterProductById(id: Int, centerProductId: CenterProductId): Array<CenterProduct>{
        return DAOCenterProduct().getCenterProductById(id,centerProductId)!!
    }

    fun getAllCenterProduct(): Array<CenterProduct>{
        return DAOCenterProduct().getCenterProducts()!!
    }

    fun createCenterProduct(centerProduct: CenterProduct): Boolean{
        return DAOCenterProduct().insertCenterProduct(centerProduct)
    }

    fun redactCenterProduct(id: Int,centerProduct: CenterProduct): Boolean{
        return DAOCenterProduct().updateCenterProduct(id, centerProduct)
    }

    //second param describe by witch id select objects
    fun deleteCenterProductById(id: Int, centerProductId: CenterProductId): Boolean{
        return DAOCenterProduct().removeCenterProductById(id, centerProductId)
    }

}