package com.example.scanbite_v1_camera_function.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.net.Inet4Address

@Entity(
    tableName = "account_table",
   /* foreignKeys = [
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
    )
    ]*/
)
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email")val email: String,
    @ColumnInfo(name = "password")val password: String,
    @ColumnInfo(name = "phoneNumber")val phoneNumber: String,
    @ColumnInfo(name = "address")val address: String,
    @ColumnInfo(name = "province")val province: String,
    @ColumnInfo(name = "favoriteProductID")val favoriteProductID: Int,
    @ColumnInfo(name = "adminID")val adminID: Int
)


