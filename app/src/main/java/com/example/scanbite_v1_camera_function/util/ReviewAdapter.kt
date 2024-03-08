package com.example.scanbite_v1_camera_function.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scanbite_v1_camera_function.databinding.EachReviewItemBinding

class ReviewAdapter(private val list: MutableList<ReviewData>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    private var listener : IReviewAdapterClicks? = null
    fun setListener(listener:IReviewAdapterClicks){
        this.listener = listener
    }
    inner class ReviewHolder(val binding: EachReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val binding = EachReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.reviewTask.text = this.review

                binding.deleteReview.setOnClickListener{
                    listener?.btnDeleteReviewClicked(this)
                }

                binding.editReview.setOnClickListener{
                    listener?.btnEditReviewClicked(this)
                }
            }
        }
    }

    interface IReviewAdapterClicks{
        fun btnDeleteReviewClicked(ReviewData : ReviewData)
        fun btnEditReviewClicked(ReviewData : ReviewData)
    }
}