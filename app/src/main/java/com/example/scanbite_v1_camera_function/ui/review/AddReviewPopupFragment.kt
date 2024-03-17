package com.example.scanbite_v1_camera_function.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.scanbite_v1_camera_function.databinding.FragmentAddReviewPopupBinding
import com.example.scanbite_v1_camera_function.util.ReviewData
import com.google.android.material.textfield.TextInputEditText

class AddReviewPopupFragment : DialogFragment() {
    private lateinit var binding: FragmentAddReviewPopupBinding
    private lateinit var nextListener: ibtnNextClickListener
    private var reviewData: ReviewData? = null

    fun setListener(nextListener: ibtnNextClickListener) {
        this.nextListener = nextListener
    }

    companion object {
        const val TAG = "AddReviewPopupFragment"

        @JvmStatic
        fun newInstance(reviewId: String, review: String) = AddReviewPopupFragment().apply {
            arguments = Bundle().apply {
                putString("reviewId", reviewId)
                putString("review", review)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddReviewPopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            reviewData = ReviewData(
                arguments?.getString("reviewId").toString(),
                arguments?.getString("review").toString()
            )
            binding.etReview.setText(reviewData?.review)
        }
        registerReview()
    }

    private fun registerReview() {
        binding.btnNext.setOnClickListener {
            val reviewTask = binding.etReview.text.toString()
            if (reviewTask.isNotEmpty()) {
                if (reviewData == null){
                    nextListener.onSaveReview(reviewTask, binding.etReview)
                } else {
                    reviewData?.review = reviewTask
                    nextListener.onUpdateReview(reviewData!!, binding.etReview)
                }
            } else {
                Toast.makeText(context, "Please type some review", Toast.LENGTH_SHORT).show()
            }
        }
        binding.reviewClose.setOnClickListener {
            dismiss()
        }
    }

    interface ibtnNextClickListener {
        fun onSaveReview(review: String, etReview: TextInputEditText)
        fun onUpdateReview(reviewData: ReviewData, etReview: TextInputEditText)
    }
}
