package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "version")
data class Version(
    @PrimaryKey var id:Long,
    @ColumnInfo var name:String,
    @ColumnInfo var url: String,
    @ColumnInfo var price: String,
    var compatibleOptions:List<Option>
)


