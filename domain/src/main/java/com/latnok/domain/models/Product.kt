package com.latnok.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val currencyCode: String,
    val currencySymbol: String,
    val quantity: Int,
    val imageLocation: String,
    val status: String
) : Parcelable
