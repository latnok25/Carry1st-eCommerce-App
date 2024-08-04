package com.latnok.carry1stecommerceapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.latnok.carry1stecommerceapp.viewmodel.CartViewModel
import com.latnok.domain.models.CartItem

@Composable
fun CartScreen(viewModel: CartViewModel) {
    val cartItems by viewModel.cartItems.collectAsState()

    LazyColumn {
        items(cartItems) { cartItem ->
            CartItem(cartItem, onDeleteClick = { viewModel.removeFromCart(it.uid) })
        }
    }
}


@Composable
fun CartItem(cartItem: CartItem, onDeleteClick: (CartItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(cartItem.imageLocation),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = cartItem.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "${cartItem.price}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onDeleteClick(cartItem) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}