package com.example.tulip.learn.dbdi.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    fun insertAll(vararg products: Product)

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM product WHERE :productId == id LIMIT 1")
    fun findById(productId: Int): Product

}