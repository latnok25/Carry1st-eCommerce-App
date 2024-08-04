package com.latnok.carry1stecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latnok.domain.models.CartItem
import com.latnok.domain.usecases.CartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartItemUseCase: CartItemUseCase
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> get() = _cartItemCount

    init {
        fetchAllCartItems()
    }

     fun fetchAllCartItems() {
        viewModelScope.launch {
            cartItemUseCase.GetCartItems().collect { items ->
                _cartItems.value = items
                _cartItemCount.value = items.size
            }
        }
    }

    fun addToCart(cartItem: CartItem) {
        viewModelScope.launch {
            cartItemUseCase.AddtoCart(cartItem)
            fetchAllCartItems()
        }
    }

    fun removeFromCart(uid: Int) {
        viewModelScope.launch {
            cartItemUseCase.RemoveFromCart(uid)
            fetchAllCartItems()
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartItemUseCase.ClearCart()
            fetchAllCartItems()
        }
    }
}

