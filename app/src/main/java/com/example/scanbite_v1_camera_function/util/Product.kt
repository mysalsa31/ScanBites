package com.example.scanbite_v1_camera_function.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.w3c.dom.Comment

@Entity(
    tableName = "product_table",
    /*foreignKeys = [
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
            entity = ReviewData::class,
            parentColumns = ["id"],
            childColumns = ["reviewID"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/
)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "score")val score: Int,
    @ColumnInfo(name = "category")val category: String,
    @ColumnInfo(name = "description")val description: String,
    @ColumnInfo(name = "price")val price: String,
    @ColumnInfo(name = "manufacturer")val manufacturer: String,
    @ColumnInfo(name = "favoriteProductID")val favoriteProductID: Int,
    @ColumnInfo(name = "adminID")val adminID: Int,
    @ColumnInfo(name = "barcodeID")val barcodeID:Int,
    @ColumnInfo(name = "reviewID")val reviewID:Int
)
