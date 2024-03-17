package com.example.scanbite_v1_camera_function.util

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Barcode::class,
            parentColumns = ["id"],
            childColumns = ["barcodeID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductWithBarcode(
    @PrimaryKey(autoGenerate = true)
    val ID: Int? = null,
    val name: String,
    val barcode: Int,
    val score: Int,
    val category: String,
    val description: String,
    val price: Double,
    val manufacturer: String,
    val favoriteProductID: Int,
    val adminID: Int,
    val barcodeID: Int,
    val barcodeNumber: Barcode
)

