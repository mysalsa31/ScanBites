package com.example.scanbite_v1_camera_function.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ReviewUtilTest{
    @Test
    fun `Added review returns true`(){
        val result = ReviewUtil.validateReview(
            1,
            "very good"
        )
        assertThat(result).isNotNull()
    }

    @Test
    fun `Deleted review returns null`(){
        val result = ReviewUtil.validateReview(
            1,
            "very good"
        )
        assertThat(result).isNull()
    }
}