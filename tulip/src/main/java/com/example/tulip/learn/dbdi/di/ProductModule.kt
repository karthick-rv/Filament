package com.example.tulip.learn.dbdi.di

import android.content.Context
import androidx.room.Room
import com.example.tulip.learn.dbdi.db.AppDatabase
import com.example.tulip.learn.dbdi.db.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "product_db"
        ).build()
    }

    @Provides
    fun provideRepository(appDatabase: AppDatabase): ProductRepository{
        return ProductRepository(appDatabase.productDao())
    }


    @Provides
    fun provideDispatcher(): CoroutineDispatcher{
        return Dispatchers.IO
    }

}