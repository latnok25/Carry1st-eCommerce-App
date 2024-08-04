package com.latnok.data.repository

import com.latnok.data.database.CartItemDAO
import com.latnok.domain.models.CartItem
import com.latnok.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl  @Inject constructor(
    private val cartItemDAO: CartItemDAO
) : CartRepository {

    override fun getAllCartItems(): Flow<List<CartItem>> {
        return flow {
            emit(cartItemDAO.getAllCartItems())
        }
    }

    override suspend fun addCartItem(cartItem: CartItem) {
        cartItemDAO.insertCartItem(cartItem)
    }

    override suspend fun removeCartItem(uid: Int) {
        val cartItem = cartItemDAO.getAllCartItems().find { it.uid == uid }
        cartItem?.let { cartItemDAO.deleteCartItem(it) }
    }

    override suspend fun clearCart() {
        val cartItems = cartItemDAO.getAllCartItems()
        cartItems.forEach { cartItemDAO.deleteCartItem(it) }
    }
}