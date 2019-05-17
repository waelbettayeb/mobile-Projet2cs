package com.waelkhelil.sayara_dz.database.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.waelkhelil.sayara_dz.database.model.Model

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<Model>)

    //the query can be customised by adding a sort type or any other parameter
    @Query("SELECT * FROM model where id_marque=:brand_id")

    fun allModels(brand_id :String): DataSource.Factory<Int, Model>

}