package com.example.spyfall.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place (
    @PrimaryKey(autoGenerate = true) var placeId: Int,
    var placeName: String
)