package com.example.scanbite_v1_camera_function.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities =
[
    Account :: class,
    Admin :: class,
    Barcode :: class,
    //Category :: class,
    FavoriteProduct :: class,
  //  ReviewData :: class,
    Product :: class,

],
    version = 1)
abstract class ScanBiteDb : RoomDatabase(){

    abstract fun scanBiteDao(): ScanBiteDao

    companion object{

        @Volatile
        private var INSTANCE : ScanBiteDb? = null

        fun getDatabase(context : Context): ScanBiteDb{

            var tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){

                val instance =

                    Room.databaseBuilder(
                    context.applicationContext,
                    ScanBiteDb::class.java,
                    "scanBite_db"
                ).build()
                INSTANCE= instance
                return instance
            }
        }
    }
}