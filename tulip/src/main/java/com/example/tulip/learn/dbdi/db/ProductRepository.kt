package com.example.tulip.learn.dbdi.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(val productDao: ProductDao) {

    suspend fun insertProduct(product: Product): Result<Unit> {
        try {
            withContext(Dispatchers.IO) {
                productDao.insertAll(product)
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }

        return Result.success(Unit)
    }

    suspend fun getAllProducts(): Result<List<Product>> {
        return try {
            withContext(Dispatchers.IO) {
                Result.success(productDao.getAll())
            }
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    suspend fun deleteProduct(id: Int) {
        withContext(Dispatchers.IO){
            val product = getProductById(id);
            productDao.delete(product)
        }
    }

    suspend fun getProductById(id: Int): Product {
        return withContext(Dispatchers.IO){
             productDao.findById(id)
        }
    }

}


