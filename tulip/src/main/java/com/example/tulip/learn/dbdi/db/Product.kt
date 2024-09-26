package com.example.tulip.learn.dbdi.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Product(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "product_name")val name: String,
    val description:String,
    val category: String,
    val price: Double,
    val isFavorite: Boolean
)

