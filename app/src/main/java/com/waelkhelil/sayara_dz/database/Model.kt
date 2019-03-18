package com.waelkhelil.sayara_dz.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model")
data class Model(
    @PrimaryKey var id:Long,
    @ColumnInfo var name:String,
    @ColumnInfo var url: String
)


