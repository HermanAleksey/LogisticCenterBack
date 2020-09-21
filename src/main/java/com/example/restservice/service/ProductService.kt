package com.example.restservice.service

import com.example.restservice.DAO.DAOProduct
import com.example.restservice.entity.Product

class ProductService {

    fun getProduct(id: Int): Product {
        return DAOProduct().getProduct(id)
    }

    fun getProducts(): List<Product>{
        return DAOProduct().getProducts()
    }

    fun updateProduct(id: Int, product: Product): Boolean{
        return DAOProduct().updateProduct(id,product)
    }

    fun insertProduct(product: Product): Boolean{
        return DAOProduct().insertProduct(product)
    }

    fun removeProduct(id: Int): Boolean{
        return DAOProduct().removeProduct(id)
    }

}