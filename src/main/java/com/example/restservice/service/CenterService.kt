package com.example.restservice.service

import com.example.restservice.DAO.DAOCenter
import com.example.restservice.entity.LogisticsCenter

class CenterService {

    fun getCenter(id: Int): LogisticsCenter{
        return DAOCenter().getCenter(id)!!
    }

    fun getAllCenters(): Array<LogisticsCenter>{
        return DAOCenter().getCenters()!!
    }

    fun createCenter(center: LogisticsCenter): Boolean{
        return DAOCenter().insertCenter(center)
    }

    fun redactCenter(id: Int,center: LogisticsCenter): Boolean{
        return DAOCenter().updateCenter(id,center)
    }

    fun deleteCenter(id: Int): Boolean{
        return DAOCenter().removeCenter(id)
    }

}