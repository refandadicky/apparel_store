package com.example.apparelstore.data.source.network.model.product

import com.google.gson.annotations.SerializedName

data class DetailProductResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("rating")
    val rating: RatingResponse?,
)

data class RatingResponse(
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("count")
    val count: Int?,
)
