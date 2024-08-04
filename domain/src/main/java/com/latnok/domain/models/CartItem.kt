package com.latnok.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val currencyCode: String,
    val currencySymbol: String,
    val quantity: Int,
    val imageLocation: String,
    val status: String
)