package com.example.spyfall.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var placeId: Int,
    var name: String
)