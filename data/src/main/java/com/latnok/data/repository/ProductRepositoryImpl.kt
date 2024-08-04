package com.latnok.data.repository
import com.latnok.data.api.ApiService
import com.latnok.domain.models.Product
import com.latnok.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
    override suspend fun getProductById(productId: Int): Product {
        return apiService.getProductById(productId)
    }
}