package com.example.restservice.service

import com.example.restservice.DAO.DAOCenter
import com.example.restservice.entity.LogisticsCenter

class CenterService {

    fun getCenter(id: Int): LogisticsCenter{
        return DAOCenter().getCenter(id)
    }

    fun getAllCenters(): List<LogisticsCenter>{
        return DAOCenter().getCenters()
    }

    fun insertCenter(center: LogisticsCenter): Boolean{
        return DAOCenter().insertCenter(center)
    }

    fun updateCenter(id: Int, center: LogisticsCenter): Boolean{
        return DAOCenter().updateCenter(id,center)
    }

    fun removeCenter(id: Int): Boolean{
        return DAOCenter().removeCenter(id)
    }

}