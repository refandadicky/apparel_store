package com.example.apparelstore.presentation.menudetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.example.apparelstore.data.model.ProductDetail
import com.example.apparelstore.databinding.ActivityDetailBinding
import com.refanda.restoran.presentation.menudetail.DetailViewModel
import com.refanda.restoran.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val productId = intent.getIntExtra("PRODUCT_ID", -1)

        if (productId != -1) {
            viewModel.getDetailProduct(productId.toString())
        } else {
            Toast.makeText(this, "Produk tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
        }

        observeData()
        setupAction()
    }

    private fun setupAction() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun observeData() {
        viewModel.detailResult.observe(this) { result ->
            result.proceedWhen(
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.tvDetailName.isVisible = false
                    binding.tvDetailPrice.isVisible = false
                    binding.tvDetailDesc.isVisible = false
                    binding.tvDetailCategory.isVisible = false
                    binding.tvDetailRating.isVisible = false
                    binding.ivDetailImage.isVisible = false
                    binding.tvError.isVisible = false
                },
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.tvDetailName.isVisible = true
                    binding.tvDetailPrice.isVisible = true
                    binding.tvDetailDesc.isVisible = true
                    binding.tvDetailCategory.isVisible = true
                    binding.tvDetailRating.isVisible = true
                    binding.ivDetailImage.isVisible = true
                    binding.tvError.isVisible = false

                    result.payload?.let { product ->
                        bindProductData(product)
                    }
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.tvDetailName.isVisible = false
                    binding.tvDetailPrice.isVisible = false
                    binding.tvDetailDesc.isVisible = false
                    binding.tvDetailCategory.isVisible = false
                    binding.tvDetailRating.isVisible = false
                    binding.ivDetailImage.isVisible = false
                    binding.tvError.isVisible = true
                    Toast.makeText(this, "Error: ${result.exception?.message}", Toast.LENGTH_SHORT).show()
                },
            )
        }
    }

    private fun bindProductData(product: ProductDetail) {
        with(binding) {
            tvDetailName.text = product.title
            tvDetailPrice.text = "$ ${product.price}"
            tvDetailDesc.text = product.description
            tvDetailCategory.text = product.category
            tvDetailRating.text = "${product.rating} / 5.0"
            ivDetailImage.load(product.image) {
                crossfade(true)
            }
        }
    }
}
