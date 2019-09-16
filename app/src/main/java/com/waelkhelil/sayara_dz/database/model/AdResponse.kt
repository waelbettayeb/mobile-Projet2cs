package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


data class AdResponse(
    @PrimaryKey @field:SerializedName("Prix_Minimal") val minPrice: Float,
    @ColumnInfo @field:SerializedName("Description") val description: String,
    @ColumnInfo @field:SerializedName("Couleur") val couleur:JsonObject,
    @ColumnInfo @field:SerializedName("Version") val version:JsonObject,
    @ColumnInfo @field:SerializedName("Options") val options: List<JsonObject>,
    @ColumnInfo @field:SerializedName("Images_Annonce") val marque: List<String>,
    @ColumnInfo @field:SerializedName("date") val date: String
)


