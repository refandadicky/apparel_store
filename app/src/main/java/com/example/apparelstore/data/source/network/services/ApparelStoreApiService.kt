package com.example.apparelstore.data.source.network.services

import com.example.apparelstore.BuildConfig
import com.example.apparelstore.data.source.network.model.product.DetailProductResponse
import com.example.apparelstore.data.source.network.model.product.ProductResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApparelStoreApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getDetailProduct(
        @Path("id") id: String,
    ): DetailProductResponse

    companion object {
        @JvmStatic
        operator fun invoke(): ApparelStoreApiService {
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(ApparelStoreApiService::class.java)
        }
    }
}
