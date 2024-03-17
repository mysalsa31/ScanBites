package com.example.scanbite_v1_camera_function.util

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["ProductID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: Char,
    val password: Char,
    val phoneNumber: Number,
    val address: String,
    val province: String,
    val role: String,
    val creationDate: Date,
    val ProductID: Int
)
