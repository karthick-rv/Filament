package com.example.tulip.learn.dbdi.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun productDao(): ProductDao
}