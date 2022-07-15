package com.example.onlineshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.onlineshop.R
import com.example.onlineshop.RemoveHtmlTag
import com.example.onlineshop.databinding.DetailItemRowBinding
import com.example.onlineshop.databinding.FragmentDetailProductBinding
import com.example.onlineshop.model.Image
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.model.Reviw
import com.example.onlineshop.ui.detail.ReviewAdapter

class PagerDetailAdapter():
    ListAdapter<Image, PagerDetailAdapter.ViewHolder>(ProductDiffCallback) {


    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        var iv = view.findViewById<ImageView>(R.id.ivDetailProduct)


        fun bind(image: Image) {
            Glide.with(context)
                .load(image)
                .fitCenter()
                .into(iv)


        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.detail_item_row, viewGroup, false)
        return ViewHolder(view, viewGroup.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image= getItem(position)
        holder.bind(image)
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
