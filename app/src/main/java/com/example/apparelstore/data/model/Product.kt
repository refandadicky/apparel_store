package com.example.apparelstore.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var id: Int,
    var title: String?,
    var price: Double?,
    var imageUrl: String,
) : Parcelable
