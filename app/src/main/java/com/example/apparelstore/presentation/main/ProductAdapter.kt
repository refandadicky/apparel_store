package com.example.apparelstore.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apparelstore.data.model.Product
import com.example.apparelstore.databinding.ItemProductBinding

class ProductAdapter(
    private val onClick: (Product) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val items = mutableListOf<Product>()

    fun submitData(data: List<Product>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.tvGridproductName.text = item.title
            binding.tvGridproductPrice.text = item.price.toString()
            binding.ivGridmenuImage.load(item.imageUrl)
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }
}
