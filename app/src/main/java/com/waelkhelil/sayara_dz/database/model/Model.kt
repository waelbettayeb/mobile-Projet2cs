package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Model(
    @PrimaryKey @field:SerializedName("Code_Modele") val id : String,
    @ColumnInfo @field:SerializedName("Nom_Modele") val name:String,
    @ColumnInfo @field:SerializedName("Id_Marque") val id_marque:String,
    @ColumnInfo @field:SerializedName("Image") val url: String

){
    override fun toString(): String {
        return this.name
    }
}



