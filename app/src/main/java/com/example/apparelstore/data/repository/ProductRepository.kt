package com.example.apparelstore.data.repository
import com.example.apparelstore.data.datasource.ProductDataSource
import com.example.apparelstore.data.mapper.toProductDetail
import com.example.apparelstore.data.mapper.toProductList
import com.example.apparelstore.data.model.Product
import com.example.apparelstore.data.model.ProductDetail
import com.refanda.restoran.utils.ResultWrapper
import com.refanda.restoran.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<ResultWrapper<List<Product>>>

    fun getDetailProduct(id: String): Flow<ResultWrapper<ProductDetail>>
}

class ProductRepositoryImpl(private val dataSource: ProductDataSource) : ProductRepository {
    override fun getProducts(): Flow<ResultWrapper<List<Product>>> {
        return proceedFlow {
            val result = dataSource.getProduct()
            result.toProductList()
        }
    }

    override fun getDetailProduct(id: String): Flow<ResultWrapper<ProductDetail>> {
        return proceedFlow {
            dataSource.getDetailProduct(id).toProductDetail()
        }
    }
}
