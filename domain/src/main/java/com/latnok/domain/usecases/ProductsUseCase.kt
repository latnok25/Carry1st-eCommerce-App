package com.latnok.domain.usecases

import com.latnok.domain.models.Product
import com.latnok.domain.repository.ProductRepository
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend fun execute(): List<Product> {
        return repository.getProducts()
    }
    suspend fun executeId(productId: Int): Product {
        return repository.getProductById(productId)
    }
}