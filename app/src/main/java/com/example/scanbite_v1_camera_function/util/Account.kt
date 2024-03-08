package com.example.scanbite_v1_camera_function.util

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.net.Inet4Address

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = FavoriteProduct::class,
            parentColumns = ["id"],
            childColumns = ["favoriteProductID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Account(
    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: Char,
    val password: Char,
    val phoneNumber: Number,
    val address: String,
    val province: String,
    val favoriteProductID: Int,
    val adminID: Int
)


