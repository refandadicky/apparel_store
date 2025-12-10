package com.example.apparelstore.di

import com.example.apparelstore.data.datasource.ProductDataSource
import com.example.apparelstore.data.datasource.ProductDataSourceImpl
import com.example.apparelstore.data.repository.ProductRepository
import com.example.apparelstore.data.repository.ProductRepositoryImpl
import com.example.apparelstore.data.source.network.services.ApparelStoreApiService
import com.refanda.restoran.presentation.main.MainViewModel
import com.refanda.restoran.presentation.menudetail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object AppModules {
    val networkModule =
        module {
            single<ApparelStoreApiService> { ApparelStoreApiService.invoke() }
        }

    private val datasource =
        module {
            single<ProductDataSource> { ProductDataSourceImpl(get()) }
        }

    private val repository =
        module {
            single<ProductRepository> { ProductRepositoryImpl(get()) }
        }

    private val viewModelModule =
        module {
            viewModelOf(::MainViewModel)
            viewModelOf(::DetailViewModel)
        }

    val modules =
        listOf(
            networkModule,
            datasource,
            repository,
            viewModelModule,
        )
}
