package com.example.apparelstore.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apparelstore.data.model.Product
import com.example.apparelstore.databinding.ActivityMainBinding
import com.example.apparelstore.presentation.menudetail.DetailActivity
import com.refanda.restoran.presentation.main.MainViewModel
import com.refanda.restoran.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModel()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter { product ->
            navigateToDetail(product)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()

        // Memanggil data produk saat aplikasi dibuka
        viewModel.getProducts()
    }

    private fun setupRecyclerView() {
        binding.rvListproduct.apply {
            // Menggunakan Grid Layout 2 kolom agar mirip toko online
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = productAdapter
        }
    }

    private fun observeData() {
        viewModel.productsResult.observe(this) { result ->
            result.proceedWhen(
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.rvListproduct.isVisible = false
                    binding.tvError.isVisible = false
                },
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.rvListproduct.isVisible = true
                    binding.tvError.isVisible = false

                    // Memasukkan data ke adapter
                    result.payload?.let { products ->
                        productAdapter.submitData(products)
                    }
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.rvListproduct.isVisible = false
                    binding.tvError.isVisible = true

                    Toast.makeText(
                        this,
                        "Gagal memuat data: ${result.exception?.message}",
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnEmpty = {
                    binding.pbLoading.isVisible = false
                    binding.rvListproduct.isVisible = false
                    binding.tvError.isVisible = true
                    binding.tvError.text = "Data Kosong"
                },
            )
        }
    }

    private fun navigateToDetail(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("PRODUCT_ID", product.id)
        startActivity(intent)
        Toast.makeText(this, "Clicked: ${product.id}", Toast.LENGTH_SHORT).show()
    }
}
