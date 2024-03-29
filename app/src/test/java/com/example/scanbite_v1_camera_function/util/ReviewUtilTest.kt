package com.example.scanbite_v1_camera_function.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ReviewUtilTest{
    @Test
    fun `Added review returns true`(){
        val result = ReviewUtil.validateReview(
            1,
            "very good",
            false,
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `Deleted review returns null`(){
        val result = ReviewUtil.validateReview(
            1,
            "very good",
            true
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `review longer than 250 characters returns false`(){
        val result = ReviewUtil.validateReview(
            1,
            "Ritz crackers are a timeless classic! Their buttery, flaky texture and subtle saltiness make them irresistible. Whether enjoyed on their own or paired " +
                    "with cheese or dips, they never disappoint. Perfect for snacking or adding a crunchy touch to any dish. A pantry staple for sure!",
            false
        )
        assertThat(result).isFalse()
    }
}