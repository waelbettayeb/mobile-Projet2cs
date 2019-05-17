package com.waelkhelil.sayara_dz.database.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.waelkhelil.sayara_dz.database.model.Brand

/**
 * Room data access object for accessing the [Brand] table.
 */
@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(brands: List<Brand>)

    //the query can be customised by adding a sort type or any other parameter
    @Query("SELECT * FROM brand")
    fun allBrands(): DataSource.Factory<Int, Brand>


}