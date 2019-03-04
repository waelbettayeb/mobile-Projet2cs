package com.waelkhelil.sayara_dz.database

import android.content.Context
import androidx.room.*

@Database(entities = [Brand::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    companion object Factory {
        private var DATABASE_NAME: String = "sayara_db"
        @Volatile
        private var sInstance:AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return sInstance ?: synchronized(AppDatabase::class) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                    sInstance = instance
                instance
            }
        }
    }
    abstract fun brandDao(): BrandDao
}