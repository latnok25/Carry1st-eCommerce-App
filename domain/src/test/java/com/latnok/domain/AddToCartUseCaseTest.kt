package com.latnok.domain

import com.latnok.domain.models.CartItem
import com.latnok.domain.repository.CartRepository
import com.latnok.domain.usecases.CartItemUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddToCartUseCaseTest {

    private lateinit var cartRepository: CartRepository
    private lateinit var addToCartUseCase: CartItemUseCase

    @Before
    fun setup() {
        cartRepository = mockk<CartRepository>()
        addToCartUseCase = CartItemUseCase(cartRepository)
    }

    @Test
    fun `execute adds item to cart`() = runTest {
        // Arrange
        val cartItem = CartItem(0, 1, name = "Loading...",
            imageLocation = "",
            price = 0.0,
            currencySymbol = "",
            description = "Loading description...",
            currencyCode = "",
            quantity = 1,
            status = "")
        coEvery { cartRepository.addCartItem(cartItem) } returns Unit

        // Act
        addToCartUseCase.AddtoCart(cartItem)

        // Assert
        coEvery { cartRepository.getAllCartItems() } returns flowOf(listOf(cartItem))
    }
}