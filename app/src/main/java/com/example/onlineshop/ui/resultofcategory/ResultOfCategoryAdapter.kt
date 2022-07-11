package com.example.onlineshop.ui.resultofcategory

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
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.model.ProductItem

typealias onClickItem= (ProductItem) ->Unit
class ResultOfCategoryAdapter(var click: onClickItem) :
    ListAdapter<ProductItem, ResultOfCategoryAdapter.ViewHolder>(ProductItemDiffCallback) {
    class ViewHolder(var view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val ivResultCategory: ImageView? = view.findViewById(R.id.ivResultCategory)
        private val tvResultCategory: TextView? =view.findViewById(R.id.tvResultCategory)

        @SuppressLint("SetTextI18n")
        fun bind(productItem: ProductItem, click: onClickItem) {
            view.setOnClickListener(){
                click.invoke(productItem)
            }
            tvResultCategory?.text= productItem.name
            if (ivResultCategory != null) {
                if(productItem.images.isNotEmpty()) {
                    Glide.with(context)
                        .load(productItem.images[0].src)
                        .fitCenter()
                        .override(400, 450)
                        .into(ivResultCategory)
                }else{
                    ""
                }
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.result_of_category_item_row, viewGroup, false)
        return ViewHolder(view,viewGroup.context)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position), click)
    }
    object ProductItemDiffCallback : DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem==newItem
        }
    }
}
