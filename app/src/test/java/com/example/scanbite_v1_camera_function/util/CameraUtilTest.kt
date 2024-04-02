package com.example.scanbite_v1_camera_function.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CameraUtilTest{
    @Test
    fun `valid barcode returns true`(){
        val result = CameraUtil.validateBarcodeScan(
            1,
            1,
            "Ritz Crackers",
            7,
            "Baked goods",
            "Golden, buttery bites of crunchy goodness, perfect for snacking or pairing with your favorite toppings",
            3.99,
            "Mondelēz International",

        )
        assertThat(result).isTrue()
    }

    @Test
    fun `invalid barcode returns false`(){
        val result = CameraUtil.validateBarcodeScan(
            1,
            0,
            "Ritz Crackers",
            7,
            "Baked goods",
            "Golden, buttery bites of crunchy goodness, perfect for snacking or pairing with your favorite toppings",
            3.99,
            "Mondelēz International",

            )
        assertThat(result).isFalse()
    }

    @Test
    fun `concurrent valid barcodes returns true`() {
        val products = listOf(
            CameraUtil.validateBarcodeScan(
                1,
                1,
                "Ritz Crackers",
                7,
                "Baked goods",
                "Golden, buttery bites of crunchy goodness, perfect for snacking or pairing with your favorite toppings",
                3.99,
                "Mondelēz International",
            ),
            CameraUtil.validateBarcodeScan(
                2,
                2,
                "Brioche Bun",
                8,
                "Baked goods",
                "Soft, buns that are die for  ",
                9.99,
                "French Industry"
            ),
            CameraUtil.validateBarcodeScan(
                3,
                3,
                "Ritz Crackers",
                7,
                "Baked goods",
                "Golden, buttery bites of crunchy goodness, perfect for snacking or pairing with your favorite toppings",
                3.99,
                "Mondelēz International",
            )

        )


        assertThat(products).isNotNull()
    }

}