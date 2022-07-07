package com.example.onlineshop.ui.home

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
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.model.Category
import com.example.onlineshop.model.ProductItem
typealias onClickItem= (ProductItem) ->Unit
class HomeRecyclerViewAdapter(var click: onClickItem) :
    ListAdapter<ProductItem, HomeRecyclerViewAdapter.ViewHolder>(ProductDiffCallback) {
    class ViewHolder(var view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val ivProduct: ImageView? = view.findViewById(R.id.ivProduct)
        private val tvTitle: TextView? = view.findViewById(R.id.tvName)
        private val tvPrice: TextView? = view.findViewById<TextView>(R.id.tvPrice)
        @SuppressLint("SetTextI18n")
        fun bind(productItem: ProductItem, click: onClickItem) {
            view.setOnClickListener(){
                click.invoke(productItem)
            }
            tvTitle?.text = productItem.name
            tvPrice?.text=productItem.price+ "تومان"
            if (ivProduct != null) {
                Glide.with(context)
                    .load(productItem.images[0].src)
                    .fitCenter()
                    .override(600,650)
                    .into(ivProduct)
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item_row, viewGroup, false)
        return ViewHolder(view,viewGroup.context)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position), click)
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