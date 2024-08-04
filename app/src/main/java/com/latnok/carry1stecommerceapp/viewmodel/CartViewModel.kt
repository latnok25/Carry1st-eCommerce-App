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

//    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
//    val cartItems: StateFlow<List<CartItem>> get() = _cartItems
//
//    val cartItemCount: StateFlow<Int> = cartItems.map { it.size }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = 0
//    )
//
//    init {
//        viewModelScope.launch {
//            _cartItems.value = cartDao.getAllCartItems()
//        }
//    }
//
//    fun addToCart(cartItem: CartItem) {
//        viewModelScope.launch {
//            cartDao.insertCartItem(cartItem)
//            _cartItems.value = cartDao.getAllCartItems()
//        }
//    }
//
//    fun removeFromCart(cartItem: CartItem) {
//        viewModelScope.launch {
//            cartDao.deleteCartItem(cartItem)
//            _cartItems.value = cartDao.getAllCartItems()
//        }
//    }


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
            fetchAllCartItems() // Refresh the cart items
        }
    }

    fun removeFromCart(uid: Int) {
        viewModelScope.launch {
            cartItemUseCase.RemoveFromCart(uid)
            fetchAllCartItems() // Refresh the cart items
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartItemUseCase.ClearCart()
            fetchAllCartItems() // Refresh the cart items
        }
    }
}

