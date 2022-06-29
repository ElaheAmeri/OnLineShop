package com.example.onlineshop.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.model.ProductItem

class RecyclerViewAdapter(var onClickItem: (Int) -> Unit) :
    ListAdapter<ProductItem, RecyclerViewAdapter.ViewHolder>(ProductDiffCallback) {
    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        val ivProduct = view.findViewById<ImageView>(R.id.ivProduct)
        val tvTitle = view.findViewById<TextView>(R.id.tvName)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        @SuppressLint("SetTextI18n")
        fun bind(productItem: ProductItem, onClickItem: (Int) -> Unit) {
            tvTitle.text = productItem.name
            tvPrice.text=productItem.price+ "تومان"
            productItem
            Glide.with(context)
                .load(productItem.images[0].src)
                .fitCenter()
                .into(ivProduct)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item_row, viewGroup, false)
        return ViewHolder(view,viewGroup.context)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position), onClickItem)
    }
    object ProductDiffCallback : DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem==newItem
        }
    }
}