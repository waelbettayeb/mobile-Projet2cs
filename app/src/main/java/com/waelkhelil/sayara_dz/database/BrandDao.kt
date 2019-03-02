package com.waelkhelil.sayara_dz.database

import androidx.room.*

@Dao
interface BrandDao {
    @Query("SELECT * FROM brand")
    fun getAll(): List<Brand>

    @Query("SELECT * FROM brand WHERE id IN (:brandIds)")
    fun loadAllByIds(brandIds: IntArray): List<Brand>

    @Query("SELECT * FROM brand WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Brand

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg Brands: Brand)

    @Delete
    fun delete(brand: Brand)
}