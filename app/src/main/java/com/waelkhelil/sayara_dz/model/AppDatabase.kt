package com.waelkhelil.sayara_dz.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.waelkhelil.sayara_dz.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Brand::class], version = 1)

abstract class AppDatabase : RoomDatabase()
{
    abstract fun brandDao(): BrandDao

    companion object Factory {
        private var DATABASE_NAME: String = "sayara_db"
        @Volatile
        private var sInstance: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = sInstance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(AppDatabase::class) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(BrandDatabaseCallback(scope)).build()
                    sInstance = instance
                return instance
            }
        }
    }

    private class BrandDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            sInstance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.brandDao())
                }
            }
        }

        fun populateDatabase(brandDao: BrandDao) {
            brandDao.deleteAll()

            var brand1 = Brand(1, "toyota", R.mipmap.ic_launcher)
            var brand2 = Brand(2, "Renault", R.drawable.test)
            brandDao.insertAll(brand1,brand2)


        }
    }
}