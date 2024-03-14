package com.example.scanbite_v1_camera_function.util

import androidx.room.RoomDatabase

abstract class ScanBiteDb: RoomDatabase() {

    abstract val scanBiteDao: ScanBiteDao
}