package com.example.apparelstore.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetail(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Double,
) : Parcelable
