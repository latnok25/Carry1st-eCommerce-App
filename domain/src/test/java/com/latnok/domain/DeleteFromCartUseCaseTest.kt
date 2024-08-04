package com.latnok.domain

import com.latnok.domain.models.CartItem
import com.latnok.domain.repository.CartRepository
import com.latnok.domain.usecases.CartItemUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteFromCartUseCaseTest {
    private lateinit var cartRepository: CartRepository
    private lateinit var deleteFromCartUseCase: CartItemUseCase

    @Before
    fun setup() {
        cartRepository = mockk<CartRepository>()
        deleteFromCartUseCase = CartItemUseCase(cartRepository)
    }

    @Test
    fun `execute removes item from cart`() = runTest {
        // Arrange
        val cartItem = CartItem(0, 1, name = "Loading...",
            imageLocation = "",
            price = 0.0,
            currencySymbol = "",
            description = "Loading description...",
            currencyCode = "",
            quantity = 1,
            status = "")
        coEvery { cartRepository.removeCartItem(cartItem.id) } returns Unit
        coEvery { cartRepository.getAllCartItems() } returns flowOf(listOf(cartItem))

        // Act
        deleteFromCartUseCase.RemoveFromCart(cartItem.id)

        // Assert
        // Verify that the item was removed from the repository
            coVerify { cartRepository.removeCartItem(cartItem.id) }


        // Check that the cart items after deletion do not include the item
        val expectedCartItems = emptyList<CartItem>()
        coEvery { cartRepository.getAllCartItems() } returns flowOf( expectedCartItems)
    }
}