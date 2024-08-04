package com.latnok.carry1stecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.latnok.carry1stecommerceapp.components.CartScreen
import com.latnok.carry1stecommerceapp.components.StoreScreen
import com.latnok.carry1stecommerceapp.components.ProductDetailView
import com.latnok.carry1stecommerceapp.components.SettingsScreen
import com.latnok.carry1stecommerceapp.viewmodel.CartViewModel
import com.latnok.carry1stecommerceapp.viewmodel.ProductViewModel

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier, productViewModel: ProductViewModel, cartViewModel: CartViewModel,) {
    NavHost(navController, startDestination = "store", modifier = modifier) {


        composable("store") { StoreScreen(viewModel = productViewModel,  navController = navController) }
        composable("cart") { CartScreen(viewModel = cartViewModel) }
        composable("settings") { SettingsScreen() }
        composable(
            "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            productViewModel.fetchProductById(productId)
            val product by productViewModel.selectedProduct.collectAsState()
            ProductDetailView(product, cartViewModel)
        }
    }
}