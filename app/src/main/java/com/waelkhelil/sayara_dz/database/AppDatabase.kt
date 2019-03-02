package com.waelkhelil.sayara_dz.database

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(Brand::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    companion object Factory {
        private val DATABASE_NAME: String = "database-sayara"
        private var sInstance:AppDatabase? = null

        fun getsInstance(context: Context): AppDatabase? {
            if (sInstance == null)
                sInstance = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, DATABASE_NAME
                                ).build()
            return sInstance
        }
    }


    abstract fun brandDao(): BrandDao
}