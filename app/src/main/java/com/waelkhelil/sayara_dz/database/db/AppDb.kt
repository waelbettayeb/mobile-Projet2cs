package com.waelkhelil.sayara_dz.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.database.model.Model

@Database(
    entities = [Brand::class, Model::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun brandsDao(): BrandDao
    abstract fun modelsDao(): ModelDao
    companion object {

        @Volatile
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDb::class.java, "sayaraDz.db")
                .build()
    }
}