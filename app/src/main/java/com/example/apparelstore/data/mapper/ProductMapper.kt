package com.example.apparelstore.data.mapper

import com.example.apparelstore.data.model.Product
import com.example.apparelstore.data.model.ProductDetail
import com.example.apparelstore.data.source.network.model.product.DetailProductResponse
import com.example.apparelstore.data.source.network.model.product.ProductResponse
import kotlin.text.orEmpty

fun List<ProductResponse>?.toProductList(): List<Product> {
    return this?.map {
        it.toProduct()
    } ?: emptyList()
}

fun ProductResponse.toProduct(): Product {
    return Product(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        price = this.price ?: 0.0,
        imageUrl = this.image.orEmpty(),
    )
}

fun DetailProductResponse.toProductDetail(): ProductDetail {
    return ProductDetail(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        price = this.price ?: 0.0,
        description = this.description.orEmpty(),
        category = this.category.orEmpty(),
        image = this.image.orEmpty(),
        rating = this.rating?.rate ?: 0.0,
    )
}
