package com.example.tulip.learn.dbdi.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.tulip.learn.flows.Post
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Timestamp

class ProductDaoTest {


    private lateinit var database: AppDatabase // Your Room Database
    private lateinit var productDao: ProductDao // Your DAO


    @Before
    fun setup() {
        // Create an in-memory database for testing
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()

        productDao = database.productDao() // Get the DAO
    }

    @After
    fun tearDown() {
        // Close the database after testing
        database.close()
    }


    @Test
    fun insertPost_retrievesPost() = runBlocking {
        val product = Product(
            id = 1234,
            name = "Wooden Table",
            description = "Foldable Steel",
            category = "Furniture",
            price = 1250.0,
            isFavorite = false
        )

        // Insert the user into the database
        productDao.insertAll(product)

        // Retrieve the user
        val retrievedUser = productDao.findById(1234)

        // Assert that the retrieved user is the same as the inserted user
        assertEquals(product, retrievedUser)
    }


}