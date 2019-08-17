package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Version(
    @PrimaryKey @field: SerializedName("Code_Version") val id : String,
    @ColumnInfo  @field: SerializedName("Nom_Version") val name : String,
    @ColumnInfo @field :SerializedName("Id_Modele") val id_modele : String
    /*@ColumnInfo var price: String,
    var compatibleOptions:Set<Option>*/
)

