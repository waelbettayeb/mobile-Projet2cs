package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ColorPrice
    (
    @PrimaryKey
    @field: SerializedName("Prix") val price : String,
    @ColumnInfo
    @field: SerializedName("Date_Debut") val date_debut : String,
    @ColumnInfo
    @field :SerializedName("Date_Fin") val date_fin : String

)