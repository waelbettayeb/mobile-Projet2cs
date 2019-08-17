package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Option(
    @PrimaryKey @field:SerializedName("Code_Option") internal var id: String,
    @ColumnInfo @field:SerializedName("Nom_Option")var name:String
)


