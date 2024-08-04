package com.latnok.carry1stecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.latnok.carry1stecommerceapp.ui.theme.Carry1stECommerceAppTheme
import com.latnok.carry1stecommerceapp.viewmodel.CartViewModel
import com.latnok.carry1stecommerceapp.viewmodel.ProductViewModel
import com.latnok.domain.models.Product
import dagger.hilt.android.AndroidEntryPoint

import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import com.latnok.carry1stecommerceapp.navigation.NavigationHost

import com.latnok.domain.models.CartItem

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Carry1stECommerceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                        MainScreen()

                }
            }
        }
    }
}






@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val productViewModel: ProductViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding),productViewModel = productViewModel,cartViewModel = cartViewModel)
    }
}



@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val cartViewModel: CartViewModel = hiltViewModel()
    val cartItemCount by cartViewModel.cartItemCount.collectAsState()
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Store Button
            IconButton(
                onClick = { navController.navigate("store") },
                modifier = Modifier.size(48.dp) // Increase icon size
            ) {
                Icon(Icons.Filled.Home, contentDescription = "Store", modifier = Modifier.size(32.dp))
            }

            // Spacer
            Spacer(modifier = Modifier.weight(1f))

            // Cart Button
            IconButton(
                onClick = { navController.navigate("cart") },
                modifier = Modifier.size(48.dp) // Increase icon size
            ) {
                BadgedBox(badge = { if (cartItemCount > 0) Badge { Text(cartItemCount.toString()) } },  modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", modifier = Modifier.size(32.dp))
                }
            }

            // Spacer
            Spacer(modifier = Modifier.weight(1f))

            // Settings Button
            IconButton(
                onClick = { navController.navigate("settings") },
                modifier = Modifier.size(48.dp) // Increase icon size
            ) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings", modifier = Modifier.size(32.dp))
            }
        }
    }
}







