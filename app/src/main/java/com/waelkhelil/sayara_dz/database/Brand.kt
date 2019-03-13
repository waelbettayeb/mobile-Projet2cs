package com.waelkhelil.sayara_dz.database

import androidx.room.*

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey var id:Long,
    @ColumnInfo var name:String
)