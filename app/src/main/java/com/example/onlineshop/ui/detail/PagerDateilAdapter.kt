package com.example.onlineshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.DetailItemRowBinding
import com.example.onlineshop.model.Image

class PagerDetailAdapter( val context: Context, var images: List<Image>)
    : RecyclerView.Adapter<PagerDetailAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val binding: DetailItemRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun setImage(imageUrl:String){
                Glide.with(context)
                    .load(imageUrl)
                    .into(binding.ivDetailProduct)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding=DetailItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {

        images[position].let { holder.setImage(it.src) }
    }

    override fun getItemCount(): Int {
        return images.size
    }

}