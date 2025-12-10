package com.example.apparelstore.data.source.network.model.product

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("rating")
    val rating: Rating? = null,
)

@Keep
data class Rating(
    @SerializedName("rate")
    val rate: Double? = null,
    @SerializedName("count")
    val count: Int? = null,
)
