package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "option")
data class Option(
    @PrimaryKey var id:Long,
    @ColumnInfo var name:String,
    @ColumnInfo val price:Int

)


