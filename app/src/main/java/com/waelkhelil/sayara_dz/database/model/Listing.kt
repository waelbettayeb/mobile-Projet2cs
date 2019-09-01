package com.waelkhelil.sayara_dz.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Annonce
@Entity(tableName = "listing")
data class Listing(
    @PrimaryKey @field:SerializedName("user_id") val id : String,
    @ColumnInfo @field:SerializedName("color") val name : String,
    @ColumnInfo @field:SerializedName("Logo") val url : String
)


