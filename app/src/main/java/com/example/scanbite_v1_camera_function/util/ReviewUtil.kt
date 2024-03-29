package com.example.scanbite_v1_camera_function.util

object ReviewUtil {

    fun validateReview(reviewId: Int, review: String, isDeleted: Boolean = false): Boolean{

        if (review.length > 250){
            return false
        }

        if (isDeleted){
            return false
        }

        return true
    }
}