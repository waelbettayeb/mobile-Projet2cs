package com.waelkhelil.sayara_dz.database

import androidx.room.*

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey var id:String,
    @ColumnInfo var name:String
)