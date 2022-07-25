package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.databinding.DetailItemRowBinding
import com.example.onlineshop.model.Image

class PagerDetailAdapter():


    ListAdapter<Image,PagerDetailAdapter.ItemHolder>(ProductDiffCallback){
class ItemHolder(val binding: DetailItemRowBinding):RecyclerView.ViewHolder(binding.root) {
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: DetailItemRowBinding =DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.detail_item_row,
            parent,false
        )
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val imageUrl=getItem(position).src
        Glide.with(holder.binding.ivDetailProduct)
            .load(imageUrl)
            .into(holder.binding.ivDetailProduct)
    }
}

object ProductDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(
        oldItem: Image,
        newItem: Image
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Image,
        newItem: Image
    ): Boolean {
        return oldItem == newItem
    }
}
