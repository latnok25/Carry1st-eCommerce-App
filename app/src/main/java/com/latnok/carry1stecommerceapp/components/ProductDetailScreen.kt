package com.latnok.carry1stecommerceapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.latnok.carry1stecommerceapp.viewmodel.CartViewModel
import com.latnok.domain.models.CartItem
import com.latnok.domain.models.Product

@Composable
fun ProductDetailView(product: Product, viewModel: CartViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter(product.imageLocation),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = product.name, style = MaterialTheme.typography.bodySmall)
        Text(text = "${product.currencySymbol}${product.price}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = {  val cartItem = CartItem(
                id = product.id,
                name = product.name,
                quantity = product.quantity, // Default quantity can be set here
                price = product.price,
                imageLocation = product.imageLocation,
                currencyCode =  product.currencyCode,
                currencySymbol = product.currencySymbol,
                description = product.description,
                status = product.status
            )
                viewModel.addToCart(cartItem)
            }) {
                Text("Add to Cart")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* Handle Buy Now */ }) {
                Text("Buy Now")
            }
        }
    }
}