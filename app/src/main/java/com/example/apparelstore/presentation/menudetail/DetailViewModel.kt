package com.refanda.restoran.presentation.menudetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apparelstore.data.model.ProductDetail
import com.example.apparelstore.data.repository.ProductRepository
import com.refanda.restoran.utils.ResultWrapper
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository,
) : ViewModel() {
    private val _detailResult = MutableLiveData<ResultWrapper<ProductDetail>>()
    val detailResult: LiveData<ResultWrapper<ProductDetail>> = _detailResult

    fun getDetailProduct(id: String) {
        viewModelScope.launch {
            repository.getDetailProduct(id).collect {
                _detailResult.value = it
            }
        }
    }
}
