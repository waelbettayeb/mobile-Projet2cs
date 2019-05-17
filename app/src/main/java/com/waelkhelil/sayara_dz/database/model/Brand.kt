package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey  @field:SerializedName("Id_Marque") val id : String,
    @ColumnInfo @field:SerializedName("Nom_Marque") val name : String,
    @ColumnInfo @field:SerializedName("Logo") val url : String
)


