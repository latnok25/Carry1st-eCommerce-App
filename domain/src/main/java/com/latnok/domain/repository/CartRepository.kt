package com.latnok.domain.repository

import com.latnok.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(): Flow<List<CartItem>>
    suspend fun addCartItem(cartItem: CartItem)
    suspend fun removeCartItem(uid: Int)
    suspend fun clearCart()
}