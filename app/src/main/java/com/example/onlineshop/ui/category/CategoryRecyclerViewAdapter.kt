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


typealias onClickItem= (CategoryItemItem) ->Unit
class CategoryRecyclerViewAdapter(var click: onClickItem) :
    ListAdapter<CategoryItemItem, CategoryRecyclerViewAdapter.ViewHolder>(CategoryDiffCallback) {
    class ViewHolder(var view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val ivCategory: ImageView? = view.findViewById(R.id.ivCategory)
        private val tvCategoryName: TextView? =view.findViewById(R.id.tvCategoryName)

        @SuppressLint("SetTextI18n")
        fun bind(category: CategoryItemItem,click: onClickItem) {
            view.setOnClickListener(){
                click.invoke(category)
            }
            tvCategoryName?.text= category.name
            if (ivCategory != null) {
                Glide.with(context)
                    .load(category.image.src)
                    .fitCenter()
                    .override(400,450)
                    .into(ivCategory)
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_item_row, viewGroup, false)
        return ViewHolder(view,viewGroup.context)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position), click)
    }
    object CategoryDiffCallback : DiffUtil.ItemCallback<CategoryItemItem>(){
        override fun areItemsTheSame(oldItem:CategoryItemItem , newItem: CategoryItemItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: CategoryItemItem, newItem: CategoryItemItem): Boolean {
            return oldItem==newItem
        }
    }
}
