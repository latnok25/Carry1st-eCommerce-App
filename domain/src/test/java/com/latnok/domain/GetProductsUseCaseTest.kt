package com.latnok.domain

import com.latnok.domain.models.Product
import com.latnok.domain.repository.ProductRepository
import com.latnok.domain.usecases.ProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetProductsUseCaseTest {
    private lateinit var productRepository: ProductRepository
    private lateinit var getProductsUseCase: ProductsUseCase

    @Before
    fun setup() {
        productRepository = mockk<ProductRepository>()
        getProductsUseCase = ProductsUseCase(productRepository)
    }

    @Test
    fun `execute returns list of products`() = runTest {
        // Arrange
        val mockProducts = listOf(  Product(1, name = "Loading...",
            imageLocation = "",
            price = 0.0,
            currencySymbol = "",
            description = "Loading description...",
            currencyCode = "",
            quantity = 1,
            status = ""))
        coEvery { productRepository.getProducts() } returns mockProducts

        // Act
        val result = getProductsUseCase.execute()

        // Assert
        assertEquals(mockProducts.first(), result.toList().first())
    }
}
