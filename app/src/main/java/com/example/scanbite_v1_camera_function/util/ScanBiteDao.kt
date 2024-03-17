package com.example.scanbite_v1_camera_function.util

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanBiteDao {
    @Upsert
    suspend fun insertProduct(product: Product)
    @Upsert
    suspend fun insertAdmin(admin: Admin)
    @Upsert
    suspend fun insertBarcode(barcode: Barcode)
    @Upsert
    suspend fun insertCategory(category: Category)
    @Upsert
    suspend fun insertAccount(account: Account)

    @Transaction
    @Query("SELECT * FROM account ORDER BY lastName")
    fun getAccountOrderedByPhoneNumber(): Flow<List<Account>>

    @Transaction
    @Query("SELECT * FROM product ORDER BY name")
    fun getAllProducts(): Flow<List<Product>>
    @Transaction
    @Query("SELECT * FROM admin ORDER BY lastName")
    fun getAllAdmin(): Flow<List<Product>>

    @Transaction
    @Query("SELECT * FROM product WHERE barcodeID= :barcodeID")
    fun getProductWithBarcode(barcodeID: Int): Product?

    @Transaction
    @Query("SELECT * FROM favoriteProduct WHERE ID= :favoriteProductID")
    fun getFavoriteProduct(favoriteProductID: Int): Product?





}