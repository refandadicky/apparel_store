package com.example.apparelstore.data.datasource

import com.example.apparelstore.data.source.network.model.product.DetailProductResponse
import com.example.apparelstore.data.source.network.model.product.ProductResponse
import com.example.apparelstore.data.source.network.services.ApparelStoreApiService

interface ProductDataSource {
    suspend fun getProduct(): List<ProductResponse>

    suspend fun getDetailProduct(id: String): DetailProductResponse
}

class ProductDataSourceImpl(
    private val service: ApparelStoreApiService,
) : ProductDataSource {
    override suspend fun getProduct(): List<ProductResponse> {
        return service.getProducts()
    }

    override suspend fun getDetailProduct(id: String): DetailProductResponse {
        return service.getDetailProduct(id)
    }
}
