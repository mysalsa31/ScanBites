package com.example.scanbite_v1_camera_function.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "admin_table",
    /*foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productID"],
            onDelete = ForeignKey.CASCADE
        )
    ]*/
)
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email")val email: Char,
    @ColumnInfo(name = "password")val password: Char,
    @ColumnInfo(name = "phoneNumber")val phoneNumber: String,
    @ColumnInfo(name = "address")val address: String,
    @ColumnInfo(name = "province")val province: String,
    @ColumnInfo(name = "role")val role: String,
    //@ColumnInfo(name = "creationDate")val creationDate: Date,
    @ColumnInfo(name = "productID")val productID: Int
)
