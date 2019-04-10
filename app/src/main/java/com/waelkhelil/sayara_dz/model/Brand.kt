package com.waelkhelil.sayara_dz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey var id:Long,
    @ColumnInfo var name:String,
    @ColumnInfo var logo: Int
    // TODO : change logo to url
)