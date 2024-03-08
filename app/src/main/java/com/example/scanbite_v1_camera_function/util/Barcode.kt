package com.example.scanbite_v1_camera_function.util

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Barcode(
    @PrimaryKey(autoGenerate = true)
    val ID: Int? = null,
    val barcodeNumber: Int
)
