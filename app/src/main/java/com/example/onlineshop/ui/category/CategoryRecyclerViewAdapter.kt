package com.example.onlineshop.ui.category

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

class CategoryRecyclerViewAdapter(var onClickItem: (Int) -> Unit) :
    ListAdapter<CategoryItemItem, CategoryRecyclerViewAdapter.ViewHolder>(ProductDiffCallback) {
    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        val ivCategory = view.findViewById<ImageView>(R.id.ivCategory)
        val tvCategoryName=view.findViewById<TextView>(R.id.tvCategoryName)

        @SuppressLint("SetTextI18n")
        fun bind(category: CategoryItemItem, onClickItem: (Int) -> Unit) {
            tvCategoryName.text= category.name
            Glide.with(context)
                .load(category.image.src)
                .fitCenter()
                .override(400,450)
                .into(ivCategory)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_item_row, viewGroup, false)
        return ViewHolder(view,viewGroup.context)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position), onClickItem)
    }
    object ProductDiffCallback : DiffUtil.ItemCallback<CategoryItemItem>(){
        override fun areItemsTheSame(oldItem:CategoryItemItem , newItem: CategoryItemItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: CategoryItemItem, newItem: CategoryItemItem): Boolean {
            return oldItem==newItem
        }
    }
}
