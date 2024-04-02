package com.example.scanbite_v1_camera_function.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barcode_table")
data class Barcode(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "barcodeNumber")val barcodeNumber: String,
    @ColumnInfo(name = "productId")val productId: Int

)
