package com.waelkhelil.sayara_dz.database

import androidx.lifecycle.*
import androidx.room.*

@Dao
interface BrandDao {
    @Query("SELECT * FROM brand")
    fun getAll(): LiveData<List<Brand>>

    @Query("SELECT * FROM brand WHERE id IN (:brandIds)")
    fun loadAllByIds(brandIds: IntArray): LiveData<List<Brand>>

    @Query("SELECT * FROM brand WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): LiveData<Brand>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg Brands: Brand)

    @Delete
    fun delete(brand: Brand)
}