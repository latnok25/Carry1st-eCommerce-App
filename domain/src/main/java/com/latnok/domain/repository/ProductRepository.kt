package com.latnok.domain.repository

import com.latnok.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(productId: Int): Product
}