package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class reservation(
    @PrimaryKey @field:SerializedName ("automobiliste") val userID: String,
    @ColumnInfo @field:SerializedName("vehicule") val carID: String,
    @ColumnInfo @field:SerializedName("date") val date : String,
    @ColumnInfo @field:SerializedName ("montant") val  montant: Float,
    @ColumnInfo @field:SerializedName ("reserver") val reserver: Boolean,
    @ColumnInfo @field:SerializedName ("Marque") val marque: String



)




