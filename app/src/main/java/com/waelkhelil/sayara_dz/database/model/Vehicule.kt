package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Vehicule (
    @PrimaryKey @field: SerializedName("Numero_Chassis") val car_id : String,
    @ColumnInfo @field: SerializedName("Marque") val brand_id : String,
    @ColumnInfo @field: SerializedName("Modele") val modele_id : String,
    @ColumnInfo @field: SerializedName("Code_Version") val version_id: String,
    @ColumnInfo @field: SerializedName("Code_Couleur") val color_id: String,
    @ColumnInfo @field: SerializedName("options_vehicule") val options :String,
    @ColumnInfo @field: SerializedName("Reservation") val reservations : List<String>


    )


