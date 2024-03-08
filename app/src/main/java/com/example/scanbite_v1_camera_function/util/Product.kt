package com.example.scanbite_v1_camera_function.util

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.w3c.dom.Comment

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = FavoriteProduct::class,
            parentColumns = ["id"],
            childColumns = ["favoriteProductID"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Admin::class,
            parentColumns = ["id"],
            childColumns = ["adminID"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Barcode::class,
            parentColumns = ["id"],
            childColumns = ["barcodeID"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Comment::class,
            parentColumns = ["id"],
            childColumns = ["commentID"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Product(
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
    val barcodeID:Int,
    val commentID:Int
)
