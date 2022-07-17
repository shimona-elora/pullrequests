package com.contus.pullrequests.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contus.pullrequests.data.model.ProductItem
import com.contus.pullrequests.databinding.ItemGroceryBinding

class PullRequestsAdapter : ListAdapter<ProductItem, PullRequestsAdapter.ViewHolder>(AdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGroceryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: ItemGroceryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ProductItem) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(binding.ivGrocery)

            binding.tvName.text = data.title

            binding.tvQuantity.text = data.quantity

            binding.tvPrice.text = data.price
        }
    }

    object AdapterDiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }
}