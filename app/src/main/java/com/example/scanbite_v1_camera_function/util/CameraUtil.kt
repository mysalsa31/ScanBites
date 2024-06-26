package com.example.scanbite_v1_camera_function.util

import com.example.scanbite_v1_camera_function.ui.api.FoodItem

object CameraUtil {

    fun validateBarcodeScan(
        ID: Int? = null,
        barcodeID:Int,
        name: String,
        score: Int,
        category: String,
        description: String,
        price: Double,
        manufacturer: String,

    ): Boolean{
        // if barcodeID is less or equal to 0 (invalid barcode) it returns false
        return barcodeID > 0
    }

}