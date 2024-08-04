package com.latnok.data.api

import com.latnok.domain.models.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("carry1stdeveloper/mock-product-api/productBundles")
    suspend fun getProducts(): List<Product>

    @GET("carry1stdeveloper/mock-product-api/productBundles/{productId}")
    suspend fun getProductById(@Path("productId") productId: Int): Product
}