package com.refanda.restoran.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apparelstore.data.model.Product
import com.example.apparelstore.data.repository.ProductRepository
import com.refanda.restoran.utils.ResultWrapper
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ProductRepository,
) : ViewModel() {
    private val _productsResult = MutableLiveData<ResultWrapper<List<Product>>>()
    val productsResult: LiveData<ResultWrapper<List<Product>>> = _productsResult

    fun getProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                _productsResult.value = result
            }
        }
    }
//    fun isLoggedIn(): Boolean {
//        return repository.isLoggedIn()
//    }
}
