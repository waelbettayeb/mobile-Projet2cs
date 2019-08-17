package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



data class PaintColor(
    @PrimaryKey @field:SerializedName("Code_Couleur") val code:String,
    @ColumnInfo @field:SerializedName("Hex_Couleur") val hexCode:String,
    @ColumnInfo @field:SerializedName("Nom_Couleur") val name:String,
    @ColumnInfo @field:SerializedName("Colore") val modele_id:List<String>
)
{
    override fun toString(): String {
        return this.name
    }
}



