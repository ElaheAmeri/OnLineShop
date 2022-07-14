package com.example.onlineshop.ui.detail

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.RemoveHtmlTag
import com.example.onlineshop.model.Reviw

typealias ReviewClickHandler = (Reviw) -> Unit

class ReviewAdapter(var onReviewClicked: ReviewClickHandler) :
    ListAdapter<Reviw, ReviewAdapter.ViewHolder>(ReviewsDiffCallback) {


    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        var ivReviewerAvatar = view.findViewById<ImageView>(R.id.ivAvatar)
        var tvReviewerName = view.findViewById<TextView>(R.id.tvNameReview)
        var tvReview = view.findViewById<TextView>(R.id.tvReview)
        var rating = view.findViewById<RatingBar>(R.id.tvRatingReview)
        val llReview = view.findViewById<ConstraintLayout>(R.id.llReview)

        fun bind(review: Reviw, onReviewClicked: ReviewClickHandler) {
            tvReviewerName.text = review.reviewer
            tvReview.text = RemoveHtmlTag.html2text(review.review)
            rating.rating = review.rating.toFloat()
            Glide.with(context)
                .load(review.reviewerAvatarUrls.x24)
                .fitCenter()
                .into(ivReviewerAvatar)
            llReview.setOnClickListener{
                onReviewClicked(review)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.review_item_row, viewGroup, false)
        return ViewHolder(view, viewGroup.context)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review, onReviewClicked)
    }

}

object ReviewsDiffCallback : DiffUtil.ItemCallback<Reviw>() {
    override fun areItemsTheSame(
        oldItem: Reviw,
        newItem: Reviw
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Reviw,
        newItem: Reviw
    ): Boolean {
        return oldItem.productId == newItem.productId
    }
}