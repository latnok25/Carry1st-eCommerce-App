package com.latnok.domain.usecases

import com.latnok.domain.models.CartItem
import com.latnok.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartItemUseCase @Inject constructor(private val repository: CartRepository) {

    suspend fun GetCartItems(): Flow<List<CartItem>> {
        return repository.getAllCartItems()
    }

    suspend fun AddtoCart(cartItem: CartItem) {
        return repository.addCartItem(cartItem)
    }

    suspend fun RemoveFromCart(uid: Int) {
        return repository.removeCartItem(uid)
    }

    suspend fun ClearCart() {
        return repository.clearCart()
    }
}