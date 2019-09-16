package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

 data class  Ad(
    @PrimaryKey @field:SerializedName("Prix_Minimal") val minPrice: Float,
    @ColumnInfo @field:SerializedName("Description") val description: String,
    @ColumnInfo @field:SerializedName("Id_Automobiliste") val idUser : String,
    @ColumnInfo @field:SerializedName("Couleur") val  idColor: String,
    @ColumnInfo @field:SerializedName("Version") val idVerion:String,
    @ColumnInfo @field:SerializedName("Options") val options: List<String>,
    @ColumnInfo @field:SerializedName("Images_Annonce") val marque: List<String>,
    @ColumnInfo @field:SerializedName("date") val date: String




)



