package com.example.scanbite_v1_camera_function.util

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface ScanBiteDao {

/*    @Query("SELECT p.id, p.name, p.score, p.description, p.price, p.manufacturer FROM product_table p" +
            "JOIN barcode_table b ON p.id = b.productId" +
            ":barcodeNumber = (SELECT REGEXP_SUBSTR(MessageText, '\\[([0-9]+)\\]', 1, 1, NULL, 1) FROM ToastMessage);" +
            "b.BarcodeNumber = :barcodeNumber  ")
    fun loadProductTableFromBarcode(barcodeNumber: String) : List<Product>*/

    //This query isn't added into the migration
   /* @Query("SELECT p.id, p.name, p.score, p.description, p.price, p.manufacturer " +
            "FROM product_table p " +
            "JOIN barcode_table b ON p.barcodeID = b.id " +
            "WHERE b.barcodeNumber = :barcodeNumber")
    fun loadProductTableFromBarcode(barcodeNumber: String): List<Product>
*/

    //This query is pull the reviewId of each product

/*
    @Query("")
    fun loadProductReviewById()
*/



}