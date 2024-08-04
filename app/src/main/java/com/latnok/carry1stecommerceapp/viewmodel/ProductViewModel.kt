package com.latnok.carry1stecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latnok.domain.usecases.ProductsUseCase
import com.latnok.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: ProductsUseCase
) : ViewModel()  {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val defaultProduct = Product(
        id = 0,
        name = "Loading...",
        imageLocation = "",
        price = 0.0,
        currencySymbol = "",
        description = "Loading description...",
        currencyCode = "",
        quantity = 1,
        status = "")
    private val _selectedProduct = MutableStateFlow<Product>(defaultProduct)
    val selectedProduct: StateFlow<Product> get() = _selectedProduct

    init {
        viewModelScope.launch {
            try {
                _products.value = getProductsUseCase.execute()
            } catch (_: Exception) {
            }
        }
    }

    fun fetchProductById(productId: Int) {
        viewModelScope.launch {
            try {
                val product = getProductsUseCase.executeId(productId)
                _selectedProduct.value = product
            } catch (_: Exception) {
            }
        }
    }
}
