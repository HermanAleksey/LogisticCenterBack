package com.example.restservice.service

import com.example.restservice.DAO.DAOProduct
import com.example.restservice.entity.Product

class ProductService {

    fun getProduct(id: Int): Product {
        return DAOProduct().getProduct(id)!!
    }

    fun getProducts(): Array<Product>{
        return DAOProduct().getProducts()!!
    }

    fun redactProduct(id: Int, product: Product): Boolean{
        return DAOProduct().updateProduct(id,product)
    }

    fun createProduct(product: Product): Boolean{
        return DAOProduct().insertProduct(product)
    }

    fun deleteProduct(id: Int): Boolean{
        return DAOProduct().removeProduct(id)
    }

}