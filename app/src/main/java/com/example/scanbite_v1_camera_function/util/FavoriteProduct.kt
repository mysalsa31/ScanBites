package com.example.scanbite_v1_camera_function.util

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "favoriteProduct_table"
)
data class FavoriteProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val productName: String,


)
