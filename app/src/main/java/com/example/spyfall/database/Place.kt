package com.example.spyfall.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class Place (
    @PrimaryKey
    @ColumnInfo(name = "place_id") val placeId: Int,
    @ColumnInfo(name = "place_name") val placeName: String
)